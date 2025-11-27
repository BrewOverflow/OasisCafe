package com.oasis.cafe.app.exception;

public class DrinkNotAvailableException extends RuntimeException {
    public DrinkNotAvailableException(String message) {
        super(message);
    }
}
