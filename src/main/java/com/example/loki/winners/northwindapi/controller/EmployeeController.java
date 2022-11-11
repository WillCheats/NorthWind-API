package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Customer;
import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.exception.EntityNotFoundException;
import com.example.loki.winners.northwindapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> catchCustomerNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.toMap());
    }
}
