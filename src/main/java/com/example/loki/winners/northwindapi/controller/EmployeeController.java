package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Customer;
import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.exception.CustomerNotFoundException;
import com.example.loki.winners.northwindapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class EmployeeController {

    private EmployeeRepository repo;

    @Autowired
    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/customer/{id}")
    public Employee getCustomerById(@PathVariable Integer id) {
        return repo.findById(id).get();
    }

}
