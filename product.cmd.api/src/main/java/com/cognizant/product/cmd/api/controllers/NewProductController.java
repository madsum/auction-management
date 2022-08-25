package com.cognizant.product.cmd.api.controllers;

import com.cognizant.product.cmd.api.config.AppProperty;
import com.cognizant.product.cmd.api.dto.NewProductResponse;
import com.cognizant.product.cmd.api.models.Product;
import com.cognizant.product.cmd.api.models.ProductRequest;
import com.cognizant.product.cmd.api.repositories.ProductRepository;
import com.cognizant.product.cmd.api.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1")
public class NewProductController {

    private final String PHOTO_URL = "http://localhost:8084/api/v1/productLookup/getImage?name=";
    private final AppProperty appProperty;
    private final StorageService storageService;

    private final ProductRepository productRepository;

    @Autowired
    public NewProductController(AppProperty appProperty, StorageService storageService, ProductRepository productRepository) {
        this.appProperty = appProperty;
        this.storageService = storageService;
        this.productRepository = productRepository;
        this.appProperty.init();
    }

    @PostMapping(path = "/addProduct")
    public ResponseEntity<NewProductResponse> addProduct(@Valid @ModelAttribute("product") ProductRequest productRequest) {
        MultipartFile file = productRequest.getFile();
        String absoluteUploadDir = System.getProperty("user.dir") + File.separator + Path.of(AppProperty.UPLOAD_DIR);
        String photoUrl = PHOTO_URL+file.getOriginalFilename();
        Product product = new Product();
        product.setProductName(productRequest.getName());
        product.setPhotoUrl(photoUrl);
        product.setPrice(productRequest.getPrice());
        product.setAuctionEndTime(productRequest.getAuctionEndTime());
        product.setBidderEmail(productRequest.getBidderEmail());
        product.setBidPrice(productRequest.getBidPrice());
        product.setSold(productRequest.isSold());

        storageService.store(file, Paths.get(absoluteUploadDir));

        var id = UUID.randomUUID().toString();

        productRepository.save(product);


        try {
            return new ResponseEntity<>(new NewProductResponse(id, "Product successfully registered!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing new product request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new NewProductResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
