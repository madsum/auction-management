package com.cognizant.product.query.api.repositories;

import com.cognizant.product.query.api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM product p WHERE p.isSold = false")
    List<Product> findBySold();
}
