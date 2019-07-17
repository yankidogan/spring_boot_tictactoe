package com.example.exercise1.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> exception (NullPointerException exception) {
        return new ResponseEntity<>("No board found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> exception (NoSuchElementException exception) {
        return new ResponseEntity<>("No player found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception (ArrayIndexOutOfBoundsException exception) {
        return new ResponseEntity<>("Please set an index between 0 and 9", HttpStatus.NOT_FOUND);
    }
}
