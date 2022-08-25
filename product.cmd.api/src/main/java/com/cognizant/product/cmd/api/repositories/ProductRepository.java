package com.cognizant.product.cmd.api.repositories;

import com.cognizant.product.cmd.api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
