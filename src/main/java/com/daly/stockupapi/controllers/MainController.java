package com.daly.stockupapi.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class MainController {
    @GetMapping("")
    public String hone() {
        return """
                <h1>   StockUp Api </h1>
                  <h3> /products to get all <a href="/products">products</a> </h3>
                  <br>
                  <h3>/id/{id} to get product by id</h3>
                  <br>
                  <h3>/add to add product</h3>
                  <br>
                  <h3>/update to update product</h3>
                  <br>
                  <h3>/delete to delete product</h3>
                  
                  
                   """;
    }
}
