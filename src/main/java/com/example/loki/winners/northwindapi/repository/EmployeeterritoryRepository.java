package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.entity.Employeeterritory;
import com.example.loki.winners.northwindapi.entity.EmployeeterritoryId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeterritoryRepository extends JpaRepository<Employeeterritory, Integer> {
}