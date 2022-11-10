package com.example.loki.winners.northwindapi.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomerNotFoundException extends RuntimeException {
    private int status;
    private String message;

    public CustomerNotFoundException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("status code", "" + status);
        map.put("message", message);
        return map;
    }
}
