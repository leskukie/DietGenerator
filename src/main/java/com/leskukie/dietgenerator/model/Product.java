package com.leskukie.dietgenerator.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;

@Builder
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private float energy;

	@Column(nullable = false)
	private float proteins;

	@Column(nullable = false)
	private float fats;

	@Column(nullable = false)
	private float carbs;

	@Column(nullable = false)
	private List<ProductType> types;
}
