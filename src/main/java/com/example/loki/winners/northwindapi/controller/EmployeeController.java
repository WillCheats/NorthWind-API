package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.model.entity.Employee;
import com.example.loki.winners.northwindapi.exception.EntityNotFoundException;
import com.example.loki.winners.northwindapi.model.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeRepository repo;

    @Autowired
    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow(()
                -> new EntityNotFoundException(404, "Could not find employee with id " + id + "."));
    }

    @GetMapping("/employee/all")
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }
}
