package com.leskukie.DietGenerator.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Diet {
	private String name;
	private List<Meal> meals;
}
