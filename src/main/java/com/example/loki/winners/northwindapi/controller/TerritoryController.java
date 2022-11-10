package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.entity.Employeeterritory;
import com.example.loki.winners.northwindapi.repository.EmployeeRepository;
import com.example.loki.winners.northwindapi.repository.EmployeeterritoryRepository;
import com.example.loki.winners.northwindapi.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.loki.winners.northwindapi.entity.Territory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
    public class TerritoryController {
    private TerritoryRepository territoryRepository;

    private EmployeeterritoryRepository employeeterritoryRepository;
    private EmployeeRepository employeeRepository;
    @Autowired
    public TerritoryController(TerritoryRepository territoryRepository, EmployeeterritoryRepository employeeterritoryRepository) {
        this.territoryRepository = territoryRepository;
        this.employeeterritoryRepository = employeeterritoryRepository;
    }


    @GetMapping("/territory/all")
    public List<Territory> getTerritory(){
        List<Territory> territoryList = territoryRepository.findAll();
        return territoryList;
    }

    @GetMapping("/territory/all2")
    public Map<Territory,List<Employee>> getTerritoryByID(){
        Map<Territory,List<Employee>> map = new HashMap<>();
        List<Territory> territoryList = territoryRepository.findAll();
        List<Employeeterritory> employeeterritoryRepositoryList = employeeterritoryRepository.findAll();

        for(Territory territory: territoryList)
        {
            List<Employee> employeeList = new ArrayList<>();
            for(Employeeterritory employeeterritory: employeeterritoryRepositoryList)
            {
                if(employeeterritory.getTerritoryID().equals(territory))
                {
                    employeeList.add(employeeterritory.getEmployeeID());
                }
            }
            map.put(territory, employeeList);
        }

        return map;
    }

}



