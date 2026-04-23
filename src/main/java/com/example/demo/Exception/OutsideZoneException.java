package com.example.demo.Exception;

public class OutsideZoneException extends RuntimeException {
    public OutsideZoneException(String message) {
        super(message);
    }
}
