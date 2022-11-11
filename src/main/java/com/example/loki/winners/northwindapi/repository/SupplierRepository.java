package com.example.loki.winners.northwindapi.repository;

import com.example.loki.winners.northwindapi.entity.Product;
import com.example.loki.winners.northwindapi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    // Basic Getters
    public List<Supplier> getAllByCompanyName  (String CompanyName);
    public List<Supplier> getAllByContactName  (String ContactName);
    public List<Supplier> getAllByContactTitle (String ContactTitle);
    public List<Supplier> getAllByAddress      (String Address);
    public List<Supplier> getAllByCity         (String City);
    public List<Supplier> getAllByRegion       (String Region);
    public List<Supplier> getAllByPostalCode   (String postalCode);
    public List<Supplier> getAllByCountry      (String country);
    public List<Supplier> getAllByPhone        (String Phone);
    public List<Supplier> getAllByFax          (String Fax);

    public boolean existsById (int id);
}