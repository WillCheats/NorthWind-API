package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}