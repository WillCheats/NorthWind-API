package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Employee;
import com.example.loki.winners.northwindapi.entity.Employeeterritory;
import com.example.loki.winners.northwindapi.entity.Region;
import com.example.loki.winners.northwindapi.exception.EntityNotFoundException;
import com.example.loki.winners.northwindapi.repository.EmployeeRepository;
import com.example.loki.winners.northwindapi.repository.EmployeeterritoryRepository;
import com.example.loki.winners.northwindapi.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.loki.winners.northwindapi.entity.Territory;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TerritoryController {
    private TerritoryRepository territoryRepository;
    private EmployeeterritoryRepository employeeterritoryRepository;

    @Autowired
    public TerritoryController(TerritoryRepository territoryRepository, EmployeeterritoryRepository employeeterritoryRepository) {
        this.territoryRepository = territoryRepository;
        this.employeeterritoryRepository = employeeterritoryRepository;
    }

    @GetMapping("/territory/{id}")
    public Map<String, Object> getTerritoryByID(@PathVariable String id) {
        Territory territory = territoryRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(404, "Could not find territory with id " + id + "."));
        return getTerritoryMap(territory, getRegionLink(territory), getEmployeeMap(territory));
    }

    @GetMapping("/territory/all")
    public List<Map<String, Object>> getTerritoryByID() {
        List<Map<String, Object>> territoryMaps = new ArrayList<>();

        List<Territory> territoryList = territoryRepository.findAll();
        List<Employeeterritory> employeeterritoryRepositoryList = employeeterritoryRepository.findAll();

        for (Territory territory : territoryList) {
            Map<String, Link> employeeMap = getEmployeeMap(territory);
            Link region = getRegionLink(territory);
            territoryMaps.add(getTerritoryMap(territory, region, employeeMap));
        }

        return territoryMaps;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> catchTerritoryNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.toMap());
    }

    private Link getRegionLink(Territory territory) {
        Region region = territory.getRegionID();
        return linkTo(methodOn(RegionController.class)
                .getRegionById(region.getId()))
                .withRel("region " + region.getId());
    }

    private Map<String, Link> getEmployeeMap(Territory territory) {
        Map<String, Link> employeeMap = new HashMap<>();
        List<Employeeterritory> employeeterritories
                = employeeterritoryRepository.getEmployeeterritoriesByTerritoryID(territory);
        for (Employeeterritory employeeterritory : employeeterritories) {
            Employee employee = employeeterritory.getEmployeeID();
            Link link = linkTo(methodOn(EmployeeController.class)
                    .getEmployeeById(employee.getId()))
                    .withRel("employee " + employee.getId());
            employeeMap.put(employee.getFirstName() + " " + employee.getLastName(), link);
        }
        return employeeMap;
    }

    private Map<String, Object> getTerritoryMap(Territory territory, Link region, Map<String, Link> employeeMap) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", territory.getId());
        map.put("description", territory.getTerritoryDescription());
        map.put("region", region);
        map.put("employees", employeeMap);
        return map;
    }


}



