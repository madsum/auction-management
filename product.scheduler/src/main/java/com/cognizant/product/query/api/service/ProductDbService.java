package com.cognizant.product.query.api.service;

import com.cognizant.product.query.api.models.Product;
import com.cognizant.product.query.api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDbService {

    private final ProductRepository productRepository;

    public ProductDbService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findBySold(){
        List<Product> result = productRepository.findBySold();
        return result;
    }

    public void updateProduct(Product product){
        productRepository.save(product);
    }
}
