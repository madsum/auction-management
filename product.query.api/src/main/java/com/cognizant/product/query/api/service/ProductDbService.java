package com.cognizant.product.query.api.service;

import com.cognizant.core.models.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDbService {
    private final MongoTemplate mongoTemplate;

    public ProductDbService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void updateProduct(Product product){
        mongoTemplate.save(product);
    }
    public List<Product> findBySold(){
        Query query = new Query().addCriteria(Criteria.where("isSold").is(false));
        List<Product> result = mongoTemplate.find(query, Product.class);
        return result;
    }
}
