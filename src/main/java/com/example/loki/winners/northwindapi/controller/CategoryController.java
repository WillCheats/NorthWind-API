package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Category;
import com.example.loki.winners.northwindapi.exception.EntityNotFoundException;
import com.example.loki.winners.northwindapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


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
                -> new EntityNotFoundException(404, "Could not find category with id " + id + "."));
    }

}
