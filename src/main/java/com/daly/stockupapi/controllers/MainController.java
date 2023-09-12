package com.daly.stockupapi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class MainController {
    @GetMapping("")
    public String hone() {
        return "StockUp Api";
    }
}
