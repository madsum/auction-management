package com.cognizant.product.cmd.api.service;

import com.cognizant.product.cmd.api.models.Product;
import com.cognizant.product.cmd.api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDbService {

    private final ProductRepository productRepository;

    public ProductDbService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findBySold(){
        List<Product> result = productRepository.findAll();
        List<Product> unSoldProduct = new ArrayList<>();
        result.forEach(p -> {
            if(!p.isSold()){
                unSoldProduct.add(p);
            }

        });
        return unSoldProduct;
    }

    public void updateProduct(Product product){
        productRepository.save(product);
    }
}
