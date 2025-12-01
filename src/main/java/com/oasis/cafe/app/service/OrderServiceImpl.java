package com.oasis.cafe.app.service;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.stereotype.Service;

import com.oasis.cafe.app.constant.CafeConstants;
import com.oasis.cafe.app.exception.OrderNotFoundException;
import com.oasis.cafe.app.model.Drink;
import com.oasis.cafe.app.model.Order;
import com.oasis.cafe.app.model.OrderItem;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final DrinkService drinkService;

    public OrderServiceImpl(OrderDAO orderDAO, OrderItemDAO orderItemDAO, DrinkService drinkService) {
        this.orderDAO = orderDAO;
        this.orderItemDAO = orderItemDAO;
        this.drinkService = drinkService;
    }

    @Override
    public Order addDrinkToOrder(Long orderId, Long drinkId) {

        Order order = orderDAO.findById(orderId).orElseGet(() -> {
            return orderDAO.save(new Order());
        });

        Drink drink = drinkService.findDrinkById(drinkId);
        OrderItem item = orderItemDAO.save(new OrderItem(order, drinkId, drink.getPrice()));
        order.addItem(item);

        return orderDAO.save(order);
    }

    @Override
    public Order getOrderByOrderNumber(String orderNumber) {

        Order order = (Order) orderDAO.findOrdersByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Drink with Order Number " + orderNumber + " is not found"));

        double total = 0;
        for (OrderItem item : order.getItems()) {
            Drink drink = drinkService.findDrinkById(item.getDrinkId());
            if (drink != null) {
                total += applyDecemberDiscount(drink).getPrice();
            }
        }
        order.setTotalPrice(total);
        return order;
    }

    private Drink applyDecemberDiscount(Drink d) {
        if (LocalDate.now().getMonth() == Month.DECEMBER) {
            d.setPrice(d.getPrice() * (1 - CafeConstants.DECEMBER_DISCOUNT));
        }
        return d;
    }
}
