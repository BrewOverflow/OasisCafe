package com.oasis.cafe.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_COFFEE")
public class Coffee extends Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CoffeeType coffeeType;

    private boolean hasMilk;

    private static int orderSequence = 0;

    public Coffee() {
        super("", DrinkSize.MEDIUM, 0.0); // default constructor for JPA
    }

    public Coffee(String name, DrinkSize size, double price, CoffeeType coffeeType, boolean hasMilk) {
        super(name, size, price);
        this.coffeeType = coffeeType;
        this.hasMilk = hasMilk;
    }

    public Long getId() { return id; }

    public CoffeeType getCoffeeType() { return coffeeType; }
    public void setCoffeeType(CoffeeType coffeeType) { this.coffeeType = coffeeType; }

    public boolean isHasMilk() { return hasMilk; }
    public void setHasMilk(boolean hasMilk) { this.hasMilk = hasMilk; }

}
