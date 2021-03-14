package com.leskukie.dietgenerator.repository;

import com.leskukie.dietgenerator.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
