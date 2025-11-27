package com.oasis.cafe.app.service;

import com.oasis.cafe.app.exception.DrinkNotAvailableException;
import com.oasis.cafe.app.exception.DrinkNotFoundException;
import com.oasis.cafe.app.model.Order;

public interface OrderService {

    Order addDrinkToOrder(Long orderId, Long drinkId) throws DrinkNotFoundException, DrinkNotAvailableException;
    Order getOrder(Long orderId) throws DrinkNotFoundException, DrinkNotAvailableException;

}
