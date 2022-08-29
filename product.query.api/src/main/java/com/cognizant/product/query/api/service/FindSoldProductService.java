package com.cognizant.product.query.api.service;

import com.cognizant.core.models.Product;
import com.cognizant.product.query.api.repositories.ProductRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindSoldProductService {

    private final MongoTemplate mongoTemplate;

    public FindSoldProductService(ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Product> findAllProduct(){
        return mongoTemplate.findAll(Product.class);
    }

    List<Product> findBySold(){
        Query query = new Query()
                .addCriteria(Criteria.where("product.isSold").is(true));
        List<Product> result = mongoTemplate.find(query, Product.class);
        return result;
    }
}
