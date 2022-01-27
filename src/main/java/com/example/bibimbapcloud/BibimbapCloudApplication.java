package com.example.bibimbapcloud;

import com.example.bibimbapcloud.domain.Ingredient;
import com.example.bibimbapcloud.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BibimbapCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibimbapCloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Ingredient("KORC", "Korean Rice", Ingredient.Type.RICE));
                repo.save(new Ingredient("VIRC", "Vietnam Rice", Ingredient.Type.RICE));
                repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                repo.save(new Ingredient("GHPK", "Ground Pork", Ingredient.Type.PROTEIN));
                repo.save(new Ingredient("CART", "Carrot", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("SPNC", "spinach", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("KOCJ", "Kochujang", Ingredient.Type.SAUCE));
                repo.save(new Ingredient("MISO", "Miso", Ingredient.Type.SAUCE));
            }
        };
    }

}
