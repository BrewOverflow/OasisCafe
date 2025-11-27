package com.oasis.cafe.app.service;

import java.util.List;

import com.oasis.cafe.app.model.Drink;

public interface DrinkService {

    List<Drink> getAllDrinks();
    List<Drink> searchDrinks(String keyword);
    Drink findDrinkById(Long id);

}
