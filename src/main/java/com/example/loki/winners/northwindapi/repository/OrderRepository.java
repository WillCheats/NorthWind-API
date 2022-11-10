package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}