package com.ncs.cafe.app.menu.model;

/**
 * Main categories for menu items.
 * This provides the hierarchy structure.
 */
public enum ItemCategory {
    // Beverages
    COFFEE("Coffee", ItemType.DRINK),
    TEA("Tea", ItemType.DRINK),
    JUICE("Juice", ItemType.DRINK),
    SODA("Soda", ItemType.DRINK),

    // Food
    BAKERY("Bakery", ItemType.FOOD),
    SANDWICH("Sandwich", ItemType.FOOD),
    SALAD("Salad", ItemType.FOOD),
    DESSERT("Dessert", ItemType.FOOD),
    SNACK("Snack", ItemType.FOOD);

    private final String displayName;
    private final ItemType type;

    ItemCategory(String displayName, ItemType type) {
        this.displayName = displayName;
        this.type = type;
    }

    public String getDisplayName() { return displayName; }
    public ItemType getType() { return type; }

    public boolean isBeverage() {
        return type == ItemType.DRINK;
    }

    public boolean isFood() {
        return type == ItemType.FOOD;
    }

    public static ItemCategory[] getBeverages() {
        return java.util.Arrays.stream(values())
                .filter(ItemCategory::isBeverage)
                .toArray(ItemCategory[]::new);
    }

    public static ItemCategory[] getFoodItems() {
        return java.util.Arrays.stream(values())
                .filter(ItemCategory::isFood)
                .toArray(ItemCategory[]::new);
    }
}