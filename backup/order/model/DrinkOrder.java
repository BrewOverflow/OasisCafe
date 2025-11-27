package com.ncs.cafe.app.order.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's drink order in the cafe.
 *
 * Demonstrates the rule: only one public class per file.
 * The private helper class (DrinkItem) is defined below.
 */
public class DrinkOrder {

    private Long orderId;
    private String customerName;
    private List<DrinkItem> items = new ArrayList<>();

    public DrinkOrder(Long orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public void addDrink(String drinkName, BigDecimal price, int quantity) {
        items.add(new DrinkItem(drinkName, price, quantity));
    }

    public BigDecimal getTotalAmount() {
        return items.stream()
                .map(DrinkItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "DrinkOrder{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + getTotalAmount() +
                '}';
    }
}

/**
 * Private helper class associated only with DrinkOrder.
 * Not accessible outside this source file.
 */
class DrinkItem {

    private String name;
    private BigDecimal price;
    private int quantity;

    public DrinkItem(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return name + " x" + quantity + " ($" + price + ")";
    }
}
