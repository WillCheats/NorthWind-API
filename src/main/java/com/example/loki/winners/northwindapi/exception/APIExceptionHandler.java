package com.example.loki.winners.northwindapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public static ResponseEntity<Object> catchEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.toMap());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> catchIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("status code", "404");
        map.put("message", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(map);
    }
}
