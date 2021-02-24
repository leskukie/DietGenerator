package com.leskukie.dietgenerator.controller;

import java.util.List;
import com.leskukie.dietgenerator.model.Product;
import com.leskukie.dietgenerator.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController("/products")
public class ProductController {

	private final ProductRepository productRepository;

	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") long id) {
		return this.productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product) {
		return this.productRepository.save(product);
	}

	@DeleteMapping
	public void deleteAllProducts() {
		this.productRepository.deleteAll();
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable long id) {
		this.productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		this.productRepository.deleteById(id);
	}
}
