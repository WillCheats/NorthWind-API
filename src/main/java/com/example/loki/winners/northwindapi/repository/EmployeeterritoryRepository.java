package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.entity.Employeeterritory;
import com.example.loki.winners.northwindapi.entity.EmployeeterritoryId;
import com.example.loki.winners.northwindapi.entity.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeterritoryRepository extends JpaRepository<Employeeterritory, Integer> {
    List<Employeeterritory> getEmployeeterritoriesByTerritoryID(Territory territory);
}