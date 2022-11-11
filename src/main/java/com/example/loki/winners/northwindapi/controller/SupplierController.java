package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Category;
import com.example.loki.winners.northwindapi.entity.Supplier;
import com.example.loki.winners.northwindapi.exception.CustomerNotFoundException;
import com.example.loki.winners.northwindapi.repository.CategoryRepository;
import com.example.loki.winners.northwindapi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {
    public static SupplierController controller;
    private SupplierRepository supplierRepo;

    @Autowired
    public SupplierController(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
        controller = WebMvcLinkBuilder.methodOn(this.getClass());
    }

    @GetMapping("/supplier/{id}")
    public Supplier getCategoryById(@PathVariable Integer id) {
        return supplierRepo.findById(id).orElseThrow(()
                -> new CustomerNotFoundException(404, "Could not find category with id " + id + "."));
    }

}
