package com.oasis.cafe.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_DRINK")
@Inheritance(strategy = InheritanceType.JOINED)
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;

    private boolean available = true;

    public Drink() {}

    public Drink(Long id, String name, double price, DrinkType drinkType, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.drinkType = drinkType;
        this.available = available;
    }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
