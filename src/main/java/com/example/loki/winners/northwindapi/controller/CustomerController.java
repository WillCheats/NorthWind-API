package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Customer;
import com.example.loki.winners.northwindapi.exception.CustomerNotFoundException;
import com.example.loki.winners.northwindapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerController {
    private CustomerRepository repo;

    @Autowired
    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return repo.findById(id).orElseThrow(()
                -> new CustomerNotFoundException(404, "Could not find customer with id " + id + "."));
    }

    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @GetMapping("/customer/search")
    public List<Customer> searchForCustomers(@RequestParam String query) {
        List<Customer> customers = repo.findCustomerByContactNameContainingIgnoreCase(query);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException(404, "Search with query '" + query + "' did not return any results.");
        }
        return customers;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> catchCustomerNotFoundException(CustomerNotFoundException ex) {
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
