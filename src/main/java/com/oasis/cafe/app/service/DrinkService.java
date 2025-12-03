package com.oasis.cafe.app.service;

import java.util.List;

import com.oasis.cafe.app.exception.DrinkNotAvailableException;
import com.oasis.cafe.app.exception.DrinkNotFoundException;
import com.oasis.cafe.app.model.Drink;

public interface DrinkService {

    List<Drink> getAllDrinks();
    List<Drink> searchDrinks(String keyword) throws DrinkNotFoundException;
    Drink findDrinkById(Long id) throws DrinkNotAvailableException, DrinkNotFoundException;

}
