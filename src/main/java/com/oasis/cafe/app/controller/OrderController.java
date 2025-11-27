package com.oasis.cafe.app.controller;

import com.oasis.cafe.app.exception.DrinkNotAvailableException;
import com.oasis.cafe.app.exception.DrinkNotFoundException;
import com.oasis.cafe.app.model.Order;
import com.oasis.cafe.app.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{orderId}/add/{drinkId}")
    public Order addDrinkToOrder(@PathVariable Long orderId, @PathVariable Long drinkId) {
        Order order = new Order();
        try {
            order = orderService.addDrinkToOrder(orderId, drinkId);
        } catch (DrinkNotFoundException | DrinkNotAvailableException drinkException) {
            drinkException.printStackTrace();
        }
        return order;
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        Order order = new Order();
        try {
            order = orderService.getOrder(orderId);
        } catch (DrinkNotFoundException drinkNotFoundException) {
            System.out.println("Drink Not Found: " + drinkNotFoundException.getMessage());
        } catch (DrinkNotAvailableException drinkNotAvailableException) {
            System.out.println("Drink Not Available: " + drinkNotAvailableException.getMessage());
        }
        return order;
    }
}
