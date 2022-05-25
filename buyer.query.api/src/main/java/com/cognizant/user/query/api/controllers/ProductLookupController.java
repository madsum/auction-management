package com.cognizant.user.query.api.controllers;

import com.cognizant.user.query.api.dto.ProductLookupResponse;
import com.cognizant.user.query.api.user.queries.FindAllUsersQuery;
import com.cognizant.user.query.api.user.queries.FindUserByIdQuery;
import com.cognizant.user.query.api.user.queries.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/productLookup")
public class ProductLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public ProductLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping(path = "/")
    public ResponseEntity<ProductLookupResponse> getAllUsers() {
        try {
            var query = new FindAllUsersQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(ProductLookupResponse.class)).join();

            if (response == null || response.getProducts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all product request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new ProductLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<ProductLookupResponse> getProductById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindUserByIdQuery(id);
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
            var query = new SearchUsersQuery(filter);
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
}
