package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.model.entity.Order;
import com.example.loki.winners.northwindapi.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class OrderDateController {
    private final OrderRepository orderRepo;

    @Autowired
    public OrderDateController(OrderRepository orderRepo) {this.orderRepo = orderRepo;}

    @GetMapping("/order/all")
    public List <Order> listOrdersBetweenDates(@RequestParam("startDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate){
        return orderRepo.findOrdersByOrderDateBetween(startDate.toInstant(), endDate.toInstant());
    }
}
