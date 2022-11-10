package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}