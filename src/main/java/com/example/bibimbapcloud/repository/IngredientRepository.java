package com.example.bibimbapcloud.repository;

import com.example.bibimbapcloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
