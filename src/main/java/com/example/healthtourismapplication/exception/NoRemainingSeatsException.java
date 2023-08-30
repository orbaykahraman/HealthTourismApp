package com.example.healthtourismapplication.exception;

public class NoRemainingSeatsException extends RuntimeException{

    public NoRemainingSeatsException(String message) {
        super(message);
    }
}
