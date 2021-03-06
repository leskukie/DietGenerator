package com.leskukie.dietgenerator.controller;

import com.leskukie.dietgenerator.model.Ingredient;
import com.leskukie.dietgenerator.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientController extends CrudController<Ingredient, IngredientRepository> {

	@Autowired
	public IngredientController(IngredientRepository repository) {
		super(repository);
	}
}
