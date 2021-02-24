package com.leskukie.dietgenerator.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meal {
	private String name;
	private List<Ingredient> ingredients;
}
