package com.cognizant.seller.cmd.api.controllers;

import com.cognizant.seller.cmd.api.config.AppProperty;
import com.cognizant.seller.cmd.api.dto.AddProductResponse;
import com.cognizant.seller.cmd.api.product.commands.AddProductCommand;
import com.cognizant.seller.cmd.api.repositories.ProductRepository;
import com.cognizant.seller.cmd.api.service.StorageService;
import com.cognizant.seller.cmd.api.util.MessageUtil;
import com.cognizant.user.core.configuration.AuctionMessageQueueConfig;
import com.cognizant.user.core.models.Product;
import com.google.common.io.Files;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/addProduct")
public class AddProductController {
    private final CommandGateway commandGateway;
    private final RabbitTemplate template;
    private final StorageService storageService;
    private final AppProperty appProperty;
    private final AuctionMessageQueueConfig auctionMessageQueueConfig;
    private final ProductRepository productRepository;

    @Autowired
    public AddProductController(CommandGateway commandGateway, RabbitTemplate template, StorageService storageService, AppProperty appProperty, AuctionMessageQueueConfig auctionMessageQueueConfig, ProductRepository productRepository) {
        this.commandGateway = commandGateway;
        this.template = template;
        this.storageService = storageService;
        this.appProperty = appProperty;
        this.auctionMessageQueueConfig = auctionMessageQueueConfig;
        this.productRepository = productRepository;
        this.appProperty.init();
    }

    @PostMapping
    public ResponseEntity<AddProductResponse> registerUser(@Valid @ModelAttribute AddProductCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new AddProductResponse(id, "Product added successfully!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing add product request for id - " + id;
            System.out.println(e);

            return new ResponseEntity<>(new AddProductResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/upload4")
    public String submit(@ModelAttribute Product product) {
        String name = product.getName();
        MultipartFile file = product.getFile();


        final String absoluteUploadDir = System.getProperty("user.dir") + File.separator + Path.of(AppProperty.UPLOAD_DIR);
        String fileUrl = absoluteUploadDir+ File.separator + file.getOriginalFilename();
        product.setFileUrl(fileUrl);
        byte[] fileByte = storageService.store(file, Paths.get(absoluteUploadDir));
        template.convertAndSend(auctionMessageQueueConfig.EXCHANGE,
                auctionMessageQueueConfig.ROUTING_KEY, MessageUtil.makeCustomMessage(fileByte));
        productRepository.save(product);
        return "uploaded " + file.getOriginalFilename();

    }


}
