package com.oasis.cafe.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.oasis.cafe.app.exception.DrinkNotFoundException;
import com.oasis.cafe.app.model.Drink;
import com.oasis.cafe.app.service.DrinkService;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {

    private static final Logger logger = LoggerFactory.getLogger(DrinkController.class);

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    // Get all drinks
    @GetMapping("/")
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    // Search drinks by keyword
    @GetMapping("/search")
    public List<Drink> searchDrinks(@RequestParam String keyword) {
        try {
            return drinkService.searchDrinks(keyword);
        } catch (DrinkNotFoundException drinkNotFoundException) {
            logger.error(drinkNotFoundException.toString());
        }
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Drink getDrinkById(@PathVariable Long id) {
        try {
            return drinkService.findDrinkById(id);
        } catch (Exception e) {
            logger.error("Drinks not found or not available.");
            throw new RuntimeException("Error while retrieving drink");
        }
    }
}
