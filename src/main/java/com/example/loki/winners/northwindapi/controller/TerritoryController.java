package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.entity.Employeeterritory;
import com.example.loki.winners.northwindapi.repository.EmployeeRepository;
import com.example.loki.winners.northwindapi.repository.EmployeeterritoryRepository;
import com.example.loki.winners.northwindapi.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    public List<EntityModel<Territory>> getTerritoryByID() {
        List<EntityModel<Territory>> models = new ArrayList<>();

        List<Territory> territoryList = territoryRepository.findAll();
        List<Employeeterritory> employeeterritoryRepositoryList = employeeterritoryRepository.findAll();

        for (Territory territory : territoryList) {
            EntityModel<Territory> territoryModel = EntityModel.of(territory);
            for (Employeeterritory employeeterritory : employeeterritoryRepositoryList) {
                if (employeeterritory.getTerritoryID().equals(territory)) {
                    Employee employee = employeeterritory.getEmployeeID();
                    Link link = linkTo(methodOn(EmployeeController.class)
                            .getCustomerById(employee.getId()))
                            .withRel(employee.getFirstName() + " " + employee.getLastName());
                    territoryModel.add(link);
                }
            }
            models.add(territoryModel);
        }

        return models;
    }

}



