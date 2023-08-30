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

    @ExceptionHandler(NoRemainingSeatsException.class)
    public ResponseEntity<String> handleNoRemainingSeatsException(NoRemainingSeatsException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoAppointmentFoundException.class)
    public ResponseEntity<String> handleNoAppointmentFoundException(NoAppointmentFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<String> handleInvalidDateException(InvalidDateException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckIsNotSuccessException.class)
    public ResponseEntity<String> handleCheckIsNotSuccessException(CheckIsNotSuccessException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentNotFinishedException.class)
    public ResponseEntity<String> handleAppointmentNotFinishedException(AppointmentNotFinishedException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
