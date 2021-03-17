package com.leskukie.dietgenerator.controller;

import com.leskukie.dietgenerator.model.Diet;
import com.leskukie.dietgenerator.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diets")
public class DietController extends CrudController<Diet, DietRepository> {

	@Autowired
	public DietController(DietRepository repository) {
		super(repository);
	}
}
