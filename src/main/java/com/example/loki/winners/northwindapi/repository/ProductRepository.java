package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}