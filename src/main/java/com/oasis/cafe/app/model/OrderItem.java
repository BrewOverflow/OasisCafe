package com.oasis.cafe.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many items to one order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long drinkId;

    private double price;

    public OrderItem() {}

    public OrderItem(Order order, Long drinkId, double price) {
        this.order = order;
        this.drinkId = drinkId;
        this.price = price;
    }

    public Long getId() { return id; }
    public Order getOrder() { return order; }
    public Long getDrinkId() { return drinkId; }
    public double getPrice() { return price; }
}