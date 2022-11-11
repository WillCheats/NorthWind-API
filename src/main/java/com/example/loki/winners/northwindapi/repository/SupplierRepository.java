package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Product;
import com.example.loki.winners.northwindapi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    // Basic Getters
    List<Supplier> getAllByCompanyName  (String CompanyName);
    List<Supplier> getAllByContactName  (String ContactName);
    List<Supplier> getAllByContactTitle (String ContactTitle);
    List<Supplier> getAllByAddress      (String Address);
    List<Supplier> getAllByCity         (String City);
    List<Supplier> getAllByRegion       (String Region);
    List<Supplier> getAllByPostalCode   (String postalCode);
    List<Supplier> getAllByCountry      (String country);
    List<Supplier> getAllByPhone        (String Phone);
    List<Supplier> getAllByFax          (String Fax);

    boolean existsById (int id);
}