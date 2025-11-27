package com.ncs.cafe.app.constants;

/**
 * Error messages and codes for the Cafe Management System.
 */
public final class ErrorConstants {

    private ErrorConstants() {
        // Private constructor to prevent instantiation
    }

    // Common Error Messages
    public static final String ERROR_RESOURCE_NOT_FOUND = "Resource not found: %s";
    public static final String ERROR_INVALID_INPUT = "Invalid input provided: %s";
    public static final String ERROR_DUPLICATE_ENTRY = "Duplicate entry found: %s";
    public static final String ERROR_UNAUTHORIZED_ACCESS = "Unauthorized access attempt";
    public static final String ERROR_INTERNAL_SERVER_ERROR = "Internal server error occurred";

    // Item Error Messages
    public static final String ERROR_ITEM_NOT_FOUND = "Item not found with ID: %s";
    public static final String ERROR_ITEM_NAME_EXISTS = "Item with name '%s' already exists";
    public static final String ERROR_ITEM_NOT_AVAILABLE = "Item is not available: %s";
    public static final String ERROR_ITEM_PRICE_INVALID = "Item price must be between %s and %s";
    public static final String ERROR_ITEM_QUANTITY_INVALID = "Item quantity must be between 1 and %s";

    // Order Error Messages
    public static final String ERROR_ORDER_NOT_FOUND = "Order not found with ID: %s";
    public static final String ERROR_ORDER_ALREADY_COMPLETED = "Order is already completed";
    public static final String ERROR_ORDER_ALREADY_CANCELLED = "Order is already cancelled";
    public static final String ERROR_ORDER_ITEMS_EMPTY = "Order must contain at least one item";
    public static final String ERROR_ORDER_TOTAL_INVALID = "Order total amount is invalid";

    // Payment Error Messages
    public static final String ERROR_PAYMENT_NOT_FOUND = "Payment not found with ID: %s";
    public static final String ERROR_PAYMENT_ALREADY_PROCESSED = "Payment has already been processed";
    public static final String ERROR_PAYMENT_AMOUNT_MISMATCH = "Payment amount does not match order total";
    public static final String ERROR_PAYMENT_METHOD_INVALID = "Invalid payment method: %s";

    // Validation Error Messages
    public static final String ERROR_VALIDATION_FAILED = "Validation failed for field: %s";
    public static final String ERROR_REQUIRED_FIELD = "Field '%s' is required";
    public static final String ERROR_INVALID_EMAIL = "Invalid email format: %s";
    public static final String ERROR_INVALID_PHONE = "Invalid phone number format: %s";
    public static final String ERROR_INVALID_DATE = "Invalid date format: %s";

    // Business Rule Error Messages
    public static final String ERROR_BUSINESS_RULE_VIOLATION = "Business rule violation: %s";
    public static final String ERROR_INSUFFICIENT_STOCK = "Insufficient stock for item: %s";
    public static final String ERROR_OPERATION_NOT_ALLOWED = "Operation not allowed in current state: %s";

    // Error Codes
    public static final String CODE_VALIDATION_ERROR = "VAL_001";
    public static final String CODE_NOT_FOUND = "NF_001";
    public static final String CODE_DUPLICATE_ENTRY = "DUP_001";
    public static final String CODE_BUSINESS_RULE = "BUS_001";
    public static final String CODE_AUTHENTICATION = "AUTH_001";
    public static final String CODE_INTERNAL_ERROR = "INT_001";
}