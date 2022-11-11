package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private EmployeeRepository repo;

    @Autowired
    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return repo.findById(id).get();
    }

}
