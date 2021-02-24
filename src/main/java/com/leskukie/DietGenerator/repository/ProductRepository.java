package com.leskukie.DietGenerator.repository;

import com.leskukie.DietGenerator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
