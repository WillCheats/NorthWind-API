package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Customer;
import com.example.loki.winners.northwindapi.exception.EntityNotFoundException;
import com.example.loki.winners.northwindapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                -> new EntityNotFoundException(404, "Could not find customer with id " + id + "."));
    }

    @GetMapping("/customer/all")
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @GetMapping("/customer/search")
    public List<Customer> searchForCustomers(@RequestParam String query) {
        List<Customer> customers = repo.findCustomerByContactNameContainingIgnoreCase(query);
        if (customers.isEmpty()) {
            throw new EntityNotFoundException(404, "Search with query '" + query + "' did not return any results.");
        }
        return customers;
    }
}
