package com.example.loki.winners.northwindapi.model.repository;

import com.example.loki.winners.northwindapi.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}