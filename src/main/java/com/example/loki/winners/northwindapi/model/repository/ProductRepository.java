package com.example.loki.winners.northwindapi.model.repository;

import com.example.loki.winners.northwindapi.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}