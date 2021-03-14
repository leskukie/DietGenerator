package com.leskukie.dietgenerator.controller;

import com.leskukie.dietgenerator.model.Meal;
import com.leskukie.dietgenerator.repository.MealRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meals")
public class MealController extends CrudController<Meal, MealRepository> {
}
