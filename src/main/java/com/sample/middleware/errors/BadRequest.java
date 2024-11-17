package com.sample.middleware.errors;


public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}