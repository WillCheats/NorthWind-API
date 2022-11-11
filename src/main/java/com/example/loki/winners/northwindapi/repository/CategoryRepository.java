package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}