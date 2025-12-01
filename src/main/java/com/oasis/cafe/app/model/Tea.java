package com.oasis.cafe.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_TEA")
public class Tea extends Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LeafType leafType;

    private Integer steepMinutes;

    public Tea() {}

    public Tea(Long id, String name, double price, boolean available, Long id1, LeafType leafType, Integer steepMinutes) {
        super(id, name, price, DrinkType.Tea, available);
        this.id = id1;
        this.leafType = leafType;
        this.steepMinutes = steepMinutes;
    }

    public Long getId() { return id; }

    public LeafType getLeafType() {
        return leafType;
    }

    public void setLeafType(LeafType leafType) {
        this.leafType = leafType;
    }

    public Integer getSteepMinutes() {
        return steepMinutes;
    }

    public void setSteepMinutes(Integer steepMinutes) {
        this.steepMinutes = steepMinutes;
    }
}
