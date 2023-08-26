package com.example.healthtourismapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundWithIdException.class)
    public ResponseEntity<String> handleNotFoundWithIdException(NotFoundWithIdException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameOrEmailAlreadyExistException.class)
    public ResponseEntity<String> handleUserNameAlreadyExistException(UserNameOrEmailAlreadyExistException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
}
