package com.oasis.cafe.app.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oasis.cafe.app.constant.CafeConstants;
import com.oasis.cafe.app.exception.DrinkNotAvailableException;
import com.oasis.cafe.app.exception.DrinkNotFoundException;
import com.oasis.cafe.app.model.Drink;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkDAO drinkDAO;

    public DrinkServiceImpl(DrinkDAO drinkDAO) {
        this.drinkDAO = drinkDAO;
    }

    public List<Drink> getAllDrinks() {
        return drinkDAO.findAll().stream()
                .map(this::applyDecemberDiscount)
                .collect(Collectors.toList());
    }

    @Override
    public List<Drink> searchDrinks(String keyword) {
        List<Drink> result = new ArrayList<Drink>();
        for (Drink drink : drinkDAO.findAll()) {
            if (drink.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(applyDecemberDiscount(drink));
            }
        }
        if (result.isEmpty()) {
            throw new DrinkNotFoundException("No drinks found for keyword: " + keyword);
        }
        return result;
    }

    public Drink findDrinkById(Long id) {
        Drink drink = drinkDAO.findById(id)
                .orElseThrow(() -> new DrinkNotFoundException("Drink with ID " + id + " is not found"));
        if (!drink.isAvailable()) {
            throw new DrinkNotAvailableException("Drink with ID " + id + " is not available");
        }
        return drink;
    }

    private Drink applyDecemberDiscount(Drink d) {
        if (LocalDate.now().getMonth() == Month.DECEMBER) {
            d.setPrice(d.getPrice() * (1 - CafeConstants.DECEMBER_DISCOUNT));
        }
        return d;
    }

}
