package com.cognizant.product.cmd.api.controllers;

import com.cognizant.product.cmd.api.config.AppProperty;
import com.cognizant.product.cmd.api.dto.ProductLookupResponse;
import com.cognizant.product.cmd.api.models.Product;
import com.cognizant.product.cmd.api.repositories.ProductRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/productLookup")
public class ProductLookupController {

    private final AppProperty appProperty;

    private final ProductRepository productRepository;

    @Autowired
    public ProductLookupController(AppProperty appProperty, ProductRepository productRepository) {
       // this.queryGateway = queryGateway;
        this.appProperty = appProperty;
        this.productRepository = productRepository;
        this.appProperty.init();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        try {
            var products = productRepository.findAll();

            products.forEach(product -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                if(product.getAuctionEndTime() != null){
                    product.setStrAuctionEndTime(dateFormat.format(product.getAuctionEndTime()));
                }
            });
            return products;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Failed to complete get all product request", e);
        }
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<ProductLookupResponse> getProductById(@PathVariable(value = "id") String id) {
        try {

            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get product by ID request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new ProductLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byFilter/{filter}")
    public ResponseEntity<ProductLookupResponse> searchProductByFilter(@PathVariable(value = "filter") String filter) {
        try {

            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete product search request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new ProductLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam String name) throws IOException {
        final String absoluteUploadDir = System.getProperty("user.dir") + File.separator + Path.of(AppProperty.UPLOAD_DIR);
        if(name.equalsIgnoreCase("undefined") || name == null){
            return null;
        }
        String fileUrl = absoluteUploadDir+ File.separator + name;
        InputStream in =  Files.newInputStream(Paths.get(fileUrl));
        return IOUtils.toByteArray(in);
    }
}
