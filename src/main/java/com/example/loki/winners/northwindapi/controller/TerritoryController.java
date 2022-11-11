package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.entity.Employeeterritory;
import com.example.loki.winners.northwindapi.repository.EmployeeRepository;
import com.example.loki.winners.northwindapi.repository.EmployeeterritoryRepository;
import com.example.loki.winners.northwindapi.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.loki.winners.northwindapi.entity.Territory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public List<Map<String, Object>> getTerritoryByID() {
        List<Map<String, Object>> territoryMaps = new ArrayList<>();

        List<Territory> territoryList = territoryRepository.findAll();
        List<Employeeterritory> employeeterritoryRepositoryList = employeeterritoryRepository.findAll();

        for (Territory territory : territoryList) {
            Map<String, Link> employeeMap = getEmployeeMap(employeeterritoryRepositoryList, territory);
            territoryMaps.add(getTerritoryJSON(territory, employeeMap));
        }

        return territoryMaps;
    }

    private static Map<String, Link> getEmployeeMap(List<Employeeterritory> employeeterritoryRepositoryList, Territory territory) {
        Map<String, Link> employeeMap = new HashMap<>();
        for (Employeeterritory employeeterritory : employeeterritoryRepositoryList) {
            if (employeeterritory.getTerritoryID().equals(territory)) {
                Employee employee = employeeterritory.getEmployeeID();
                Link link = linkTo(methodOn(EmployeeController.class)
                        .getEmployeeById(employee.getId()))
                        .withRel("employee" + employee.getId());
                employeeMap.put(employee.getFirstName() + " " + employee.getLastName(), link);
            }
        }
        return employeeMap;
    }

    private Map<String, Object> getTerritoryJSON(Territory territory, Map<String, Link> employeeMap) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", territory.getId());
        map.put("description", territory.getTerritoryDescription());
        map.put("employees", employeeMap);
        return map;
    }

}



