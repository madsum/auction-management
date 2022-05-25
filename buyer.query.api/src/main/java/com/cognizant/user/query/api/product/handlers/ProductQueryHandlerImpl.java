package com.cognizant.user.query.api.product.handlers;

import com.cognizant.user.query.api.dto.ProductLookupResponse;
import com.cognizant.user.query.api.repositories.ProductRepository;
import com.cognizant.user.query.api.repositories.UserRepository;
import com.cognizant.user.query.api.user.queries.FindAllUsersQuery;
import com.cognizant.user.query.api.user.queries.FindUserByIdQuery;
import com.cognizant.user.query.api.user.queries.SearchUsersQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductQueryHandlerImpl implements ProductQueryHandler {
    private final ProductRepository productRepository;

    @Autowired
    public ProductQueryHandlerImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    @Override
    public ProductLookupResponse getProductById(FindUserByIdQuery query) {
        var product = productRepository.findById(query.getId());
        return product.isPresent() ? new ProductLookupResponse(product.get()) : null;
    }

    @QueryHandler
    @Override
    public ProductLookupResponse searchProducts(SearchUsersQuery query) {
        var products = new ArrayList<>(productRepository.findByFilterRegex(query.getFilter()));
        return new ProductLookupResponse(products);
    }

    @QueryHandler
    @Override
    public ProductLookupResponse getAllProducts(FindAllUsersQuery query) {
        var products = new ArrayList<>(productRepository.findAll());
        return new ProductLookupResponse(products);
    }
}
