package com.oasis.cafe.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_CAFE_TEA")
public class Tea extends Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TeaType teaType;

    private boolean hasSugar;

    public Tea() {
        super("", DrinkSize.MEDIUM, 0.0); // default constructor for JPA
    }

    public Tea(String name, DrinkSize size, double price, TeaType teaType, boolean hasSugar) {
        super(name, size, price);
        this.teaType = teaType;
        this.hasSugar = hasSugar;
    }

    public Long getId() { return id; }

    public TeaType getTeaType() { return teaType; }
    public void setTeaType(TeaType teaType) { this.teaType = teaType; }

    public boolean isHasSugar() { return hasSugar; }
    public void setHasSugar(boolean hasSugar) { this.hasSugar = hasSugar; }

}
