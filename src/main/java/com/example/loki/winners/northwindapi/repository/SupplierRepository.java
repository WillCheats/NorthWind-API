package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}