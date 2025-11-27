package com.ncs.cafe.app.menu.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.*;

/**
 * Represents an item in the cafe system.
 * Maps to the database table TBL_CAFE_ITEM.
 */
@Entity
@Table(name = "TBL_CAFE_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

//    @Column(name = "ITEM_NAME", nullable = false, length = 100)
//    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY", nullable = false, length = 50)
    private ItemCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "ITEM_TYPE", nullable = false, length = 20)
    private ItemType type;

    @Column(name = "IS_AVAILABLE", nullable = false)
    private boolean available = true;

    @Version
    @Column(name = "VERSION")
    private Long version;

    private String name, reference; //AVOID


    public Item () { }

    // Parameterized constructor
    public Item(String name, String description, BigDecimal price,
                ItemCategory category, ItemType type, String reference) {
        this.name = Objects.requireNonNull(name, "Item name cannot be null");
        this.description = description;
        this.price = Objects.requireNonNull(price, "Price cannot be null");
        this.category = Objects.requireNonNull(category, "Category cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        this.reference = reference;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public String getItemName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public ItemCategory getCategory() { return category; }
    public void setCategory(ItemCategory category) { this.category = category; }

    public ItemType getType() { return type; }
    public void setType(ItemType type) { this.type = type; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    // Business methods
    public boolean isBeverage() {
        return type == ItemType.DRINK;
    }

    public boolean isFood() {
        return type == ItemType.FOOD;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Item item = (Item) object;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("Item{id=%d, name='%s', category=%s, price=%.2f, available=%s}",
                id, name, category, price, available);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}