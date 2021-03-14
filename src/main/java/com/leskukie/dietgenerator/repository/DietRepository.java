package com.leskukie.dietgenerator.repository;

import com.leskukie.dietgenerator.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {
}
