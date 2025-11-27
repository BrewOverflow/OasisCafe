package com.ncs.cafe.app.constants;

/**
 * Application-wide constants for the Cafe Management System.
 */
public final class AppConstants {

    private AppConstants() {
        // Private constructor to prevent instantiation
    }

    // Database Table Names
    public static final String TABLE_CAFE_ITEM = "TBL_CAFE_ITEM";
    public static final String TABLE_CAFE_ORDER = "TBL_CAFE_ORDER";
    public static final String TABLE_CAFE_ORDER_ITEM = "TBL_CAFE_ORDER_ITEM";
    public static final String TABLE_CAFE_PAYMENT = "TBL_CAFE_PAYMENT";

    // Common Column Lengths
    public static final int MAX_NAME_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 500;
    public static final int MAX_EMAIL_LENGTH = 255;
    public static final int MAX_PHONE_LENGTH = 20;
    public static final int MAX_CODE_LENGTH = 50;
    public static final int MAX_NOTES_LENGTH = 1000;

    // Pagination Defaults
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    public static final String DEFAULT_SORT_FIELD = "id";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";

    // Date/Time Formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    // API Paths
    public static final String API_BASE_PATH = "/api";
    public static final String API_VERSION_V1 = "/v1";
    public static final String ITEMS_API_PATH = "/items";
    public static final String ORDERS_API_PATH = "/orders";
    public static final String PAYMENTS_API_PATH = "/payments";

    // Cache Names
    public static final String CACHE_ITEMS = "items";
    public static final String CACHE_AVAILABLE_ITEMS = "availableItems";
    public static final String CACHE_ITEM_CATEGORIES = "itemCategories";

    // Application Properties
    public static final String APP_TIMEZONE = "UTC";
    public static final String APP_LOCALE = "en_US";
}