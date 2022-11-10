package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}