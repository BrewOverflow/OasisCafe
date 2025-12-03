package com.oasis.cafe.app.service;

import com.oasis.cafe.app.exception.DrinkNotAvailableException;
import com.oasis.cafe.app.exception.DrinkNotFoundException;
import com.oasis.cafe.app.model.Order;

public interface OrderService {

    Order addDrinkToOrder (Long orderId, Long drinkId) throws DrinkNotAvailableException, DrinkNotFoundException;
    Order getOrderByOrderNumber(String orderNumber) throws DrinkNotAvailableException, DrinkNotFoundException;

}
