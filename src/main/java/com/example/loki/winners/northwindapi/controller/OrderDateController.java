package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.entity.Order;
import com.example.loki.winners.northwindapi.repository.CustomerRepository;
import com.example.loki.winners.northwindapi.repository.OrderRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Locale;

@RestController
public class OrderDateController {
    private final OrderRepository orderRepo;

    @Autowired
    public OrderDateController(OrderRepository orderRepo) {this.orderRepo = orderRepo;}

    @GetMapping("/order/all")
    public List <Order> listOrdersBetweenDates(String startDate, String endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            return orderRepo.findOrdersByOrderDateBetween(formatter.parse(startDate).toInstant(), formatter.parse(endDate).toInstant());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
