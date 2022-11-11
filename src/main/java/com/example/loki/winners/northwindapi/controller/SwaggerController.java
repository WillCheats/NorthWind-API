package com.example.loki.winners.northwindapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping("/docs")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }
}
