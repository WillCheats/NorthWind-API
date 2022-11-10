package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Product;
import com.example.loki.winners.northwindapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    private ProductRepository productRepo;
    @Autowired
    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @PatchMapping("product")
    public void updateProduct(int id, BigDecimal unitPrice){
        Product product= productRepo.findById(id).get();
        product.setUnitPrice(unitPrice);
        productRepo.save(product);
    }
    @GetMapping("/product/all")
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    
}
