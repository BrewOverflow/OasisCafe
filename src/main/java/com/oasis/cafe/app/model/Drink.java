package com.oasis.cafe.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_DRINK")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DrinkSize size;

    private double price;

    private boolean available; // new attribute

    public Drink() {
        this.available = true; // default available
    }

    public Drink(String name, DrinkSize size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.available = true;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public DrinkSize getSize() { return size; }
    public void setSize(DrinkSize size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

}
