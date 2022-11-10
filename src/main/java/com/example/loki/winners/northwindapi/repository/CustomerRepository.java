package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findCustomerByContactNameContainingIgnoreCase(String name);
}