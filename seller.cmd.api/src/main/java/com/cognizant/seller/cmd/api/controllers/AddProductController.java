package com.cognizant.seller.cmd.api.controllers;

import com.cognizant.seller.cmd.api.config.AppProperty;
import com.cognizant.seller.cmd.api.config.MQConfig;
import com.cognizant.seller.cmd.api.product.commands.AddProductCommand;
import com.cognizant.seller.cmd.api.dto.AddProductResponse;
import com.cognizant.seller.cmd.api.service.StorageService;
import com.cognizant.seller.cmd.api.util.MessageUtil;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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

    @Autowired
    public AddProductController(CommandGateway commandGateway, RabbitTemplate template, StorageService storageService, AppProperty appProperty) {
        this.commandGateway = commandGateway;
        this.template = template;
        this.storageService = storageService;
        this.appProperty = appProperty;
        this.appProperty.init();
    }

    @PostMapping
    public ResponseEntity<AddProductResponse> registerUser(@Valid @RequestBody AddProductCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.sendAndWait(command);

            template.convertAndSend(MQConfig.EXCHANGE,
                    MQConfig.ROUTING_KEY, command);

            return new ResponseEntity<>(new AddProductResponse(id, "Product added successfully!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing add product request for id - " + id;
            System.out.println(e);

            return new ResponseEntity<>(new AddProductResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        final String absoluteUploadDir = System.getProperty("user.dir") + Path.of(AppProperty.UPLOAD_DIR);
        byte[] fileByte = storageService.store(file, Paths.get(absoluteUploadDir));
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, MessageUtil.makeCustomMessage(fileByte));
        return "uploaded "+file.getOriginalFilename();
    }
}
