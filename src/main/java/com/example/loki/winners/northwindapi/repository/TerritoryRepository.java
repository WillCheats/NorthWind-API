package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerritoryRepository extends JpaRepository<Territory, String> {
}