package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Territory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerritoryRepository extends JpaRepository<Territory, String> {
}