package com.oasis.cafe.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    private LocalDateTime orderDateTime;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items = new ArrayList<>();

    private static int orderSequence = 0;

    public Order() {
        this.orderNumber = generateOrderNumber();
        this.orderDateTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public LocalDateTime getOrderDateTime() { return orderDateTime; }
    public List<OrderItem> getItems() { return items; }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    private String generateOrderNumber() {
        orderSequence ++;
        return "ORDER-" + orderSequence;
    }
}