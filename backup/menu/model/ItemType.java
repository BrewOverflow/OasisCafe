package com.ncs.cafe.app.menu.model;

/**
 * High-level type classification.
 */
public enum ItemType {
    DRINK("Drink"),
    FOOD("Food");

    private final String displayName;

    ItemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}