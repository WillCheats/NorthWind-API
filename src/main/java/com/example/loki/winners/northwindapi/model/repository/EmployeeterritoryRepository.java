package com.example.loki.winners.northwindapi.model.repository;

import com.example.loki.winners.northwindapi.model.entity.Employeeterritory;
import com.example.loki.winners.northwindapi.model.entity.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeterritoryRepository extends JpaRepository<Employeeterritory, Integer> {
    List<Employeeterritory> getEmployeeterritoriesByTerritoryID(Territory territory);
}