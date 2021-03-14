package com.leskukie.dietgenerator.controller;

import com.leskukie.dietgenerator.model.Product;
import com.leskukie.dietgenerator.repository.ProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController extends CrudController<Product, ProductRepository> {
}
