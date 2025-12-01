package com.oasis.cafe.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_COFFEE")
public class Coffee extends Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoastLevel roastLevel; // e.g., Dark, Medium

    private Integer caffeineMg;

    public Coffee(Long id, String name, double price, boolean available, Long id1, RoastLevel roastLevel, Integer caffeineMg) {
        super(id, name, price, DrinkType.Coffee, available);
        this.id = id1;
        this.roastLevel = roastLevel;
        this.caffeineMg = caffeineMg;
    }

    public Coffee() {}

    public Long getId() { return id; }

    public RoastLevel getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(RoastLevel roastLevel) {
        this.roastLevel = roastLevel;
    }

    public Integer getCaffeineMg() {
        return caffeineMg;
    }

    public void setCaffeineMg(Integer caffeineMg) {
        this.caffeineMg = caffeineMg;
    }

}
