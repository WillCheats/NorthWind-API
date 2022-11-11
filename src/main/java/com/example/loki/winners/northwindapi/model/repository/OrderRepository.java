package com.example.loki.winners.northwindapi.model.repository;

import com.example.loki.winners.northwindapi.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List <Order> findOrdersByOrderDateBetween (Instant startDate, Instant endDate);
}