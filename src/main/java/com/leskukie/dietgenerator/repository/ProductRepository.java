package com.leskukie.dietgenerator.repository;

import com.leskukie.dietgenerator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
