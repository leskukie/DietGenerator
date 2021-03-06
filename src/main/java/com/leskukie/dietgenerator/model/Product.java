package com.leskukie.dietgenerator.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column
	private float energy;

	@Column
	private float proteins;

	@Column
	private float fats;

	@Column
	private float carbs;

	@Column
	@ElementCollection(targetClass = ProductType.class)
	@Enumerated(EnumType.STRING)
	private List<ProductType> types;

	@OneToOne(mappedBy = "product")
	private Ingredient ingredient;

}
