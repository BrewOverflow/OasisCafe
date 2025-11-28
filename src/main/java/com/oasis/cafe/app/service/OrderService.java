package com.oasis.cafe.app.service;

import com.oasis.cafe.app.model.Order;

public interface OrderService {

    Order addDrinkToOrder(Long orderId, Long drinkId);
    Order getOrder(Long orderId);

}
