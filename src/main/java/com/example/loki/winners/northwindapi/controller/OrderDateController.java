package com.example.loki.winners.northwindapi.controller;

import com.example.loki.winners.northwindapi.model.entity.Order;
import com.example.loki.winners.northwindapi.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
