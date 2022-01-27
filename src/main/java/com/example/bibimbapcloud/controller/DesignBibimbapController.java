package com.example.bibimbapcloud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bibimbapcloud.domain.Bibimbap;
import com.example.bibimbapcloud.domain.Ingredient;
import com.example.bibimbapcloud.domain.Order;
import com.example.bibimbapcloud.repository.BibimbapRepository;
import com.example.bibimbapcloud.repository.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignBibimbapController {

    private final IngredientRepository ingredientRepo;

    private final BibimbapRepository bibimbapRepo;

    @Autowired
    public DesignBibimbapController(IngredientRepository ingredientRepo, BibimbapRepository bibimbapRepo) {
        this.ingredientRepo = ingredientRepo;
        this.bibimbapRepo = bibimbapRepo;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("bibimbap", new Bibimbap());

        return "design";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "bibimbap")
    public Bibimbap bibimbap() {
        return new Bibimbap();
    }

    @PostMapping
    public String processDesign(@Valid Bibimbap design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Bibimbap designed: " + design);
        Bibimbap saved = bibimbapRepo.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }
}
