package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Category;
import com.example.loki.winners.northwindapi.entity.Customer;
import com.example.loki.winners.northwindapi.entity.Product;
import com.example.loki.winners.northwindapi.exception.CustomerNotFoundException;
import com.example.loki.winners.northwindapi.repository.CategoryRepository;
import com.example.loki.winners.northwindapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CategoryController {
    public static CategoryController controller;
    private CategoryRepository categoryRepo;

    @Autowired
    public CategoryController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
        controller = WebMvcLinkBuilder.methodOn(this.getClass());
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryRepo.findById(id).orElseThrow(()
                -> new CustomerNotFoundException(404, "Could not find category with id " + id + "."));
    }

}
