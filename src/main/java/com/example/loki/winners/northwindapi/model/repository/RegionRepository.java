package com.example.loki.winners.northwindapi.model.repository;

import com.example.loki.winners.northwindapi.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}