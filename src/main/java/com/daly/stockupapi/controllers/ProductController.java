package com.daly.stockupapi.controllers;

import com.daly.stockupapi.entities.Product;
import com.daly.stockupapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping("/products")
public class ProductController {
    final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/id/{id}")
    public Optional<Product> getProductById(@PathVariable("id") String id) {
        return productService.getProductById(id);
    }

    @GetMapping(path = {"/photo/{name}"})
    public ResponseEntity<byte[]> getProductImage(@PathVariable String name) {
        return productService.getProductPhoto(name);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestParam String name, @RequestParam MultipartFile photo, @RequestParam double price, @RequestParam int quantity) throws IOException {
        return productService.addProduct(name, photo, price, quantity);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestParam String id, @RequestParam String name, @RequestParam MultipartFile photo, @RequestParam double price, @RequestParam int quantity) throws IOException {
        return productService.updateProduct(id, name, photo, price, quantity);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam String id) {
        return productService.deleteProduct(id);
    }

}
