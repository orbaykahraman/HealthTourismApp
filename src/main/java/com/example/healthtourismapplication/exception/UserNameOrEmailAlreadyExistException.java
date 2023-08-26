package com.example.healthtourismapplication.exception;

public class UserNameOrEmailAlreadyExistException extends RuntimeException{

    public UserNameOrEmailAlreadyExistException(String message) {
        super(message);
    }
}
