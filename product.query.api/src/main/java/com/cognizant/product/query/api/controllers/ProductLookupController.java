package com.cognizant.product.query.api.controllers;

import com.cognizant.core.models.Product;
import com.cognizant.product.query.api.config.AppProperty;
import com.cognizant.product.query.api.dto.ProductLookupResponse;
import com.cognizant.product.query.api.queries.FindAllProductQuery;
import com.cognizant.product.query.api.queries.FindProductByIdQuery;
import com.cognizant.product.query.api.queries.SearchProductQuery;
import org.apache.commons.io.IOUtils;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/productLookup")
public class ProductLookupController {
    private final QueryGateway queryGateway;
    private final AppProperty appProperty;

    @Autowired
    public ProductLookupController(QueryGateway queryGateway, AppProperty appProperty) {
        this.queryGateway = queryGateway;
        this.appProperty = appProperty;
        this.appProperty.init();
    }

    @GetMapping
    public List<Product> getAllProducts() {
        try {
            var query = new FindAllProductQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(ProductLookupResponse.class)).join();

            if (response == null || response.getProducts() == null) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No product found", null);
            }
            return response.getProducts();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Failed to complete get all product request", e);
        }
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<ProductLookupResponse> getProductById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindProductByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(ProductLookupResponse.class)).join();

            if (response == null || response.getProducts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get product by ID request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new ProductLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byFilter/{filter}")
    public ResponseEntity<ProductLookupResponse> searchProductByFilter(@PathVariable(value = "filter") String filter) {
        try {
            var query = new SearchProductQuery(filter);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(ProductLookupResponse.class)).join();

            if (response == null || response.getProducts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
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
        String fileUrl = absoluteUploadDir+ File.separator + name;
        InputStream in =  Files.newInputStream(Paths.get(fileUrl));
        return IOUtils.toByteArray(in);
    }
}
