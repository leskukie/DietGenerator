package com.leskukie.dietgenerator.repository;

import com.leskukie.dietgenerator.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
