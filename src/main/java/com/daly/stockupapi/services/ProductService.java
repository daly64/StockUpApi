package com.daly.stockupapi.services;

import com.daly.stockupapi.entities.Product;
import com.daly.stockupapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public ResponseEntity<String> addProduct(Product product) {
        productRepository.save(product);
        return new ResponseEntity<>("Product created successfully ", HttpStatus.OK);
    }

    public ResponseEntity<String> updateProduct(Product product) {
        Optional<Product> toUpdateProduct = productRepository.findById(product.getId());

        if (toUpdateProduct.isPresent()) {
            Product updatedProduct = toUpdateProduct.get();

            updatedProduct.setName(product.getName());

            productRepository.save(updatedProduct);

            return new ResponseEntity<>("Product updated successfully ", HttpStatus.OK);

        }
        return new ResponseEntity<>("no product to update ", HttpStatus.NOT_FOUND);

    }


    public ResponseEntity<String> deleteProduct(Product product) {
        Optional<Product> toDeleteProduct = productRepository.findById(product.getId());

        if (toDeleteProduct.isPresent()) {
            productRepository.delete(toDeleteProduct.get());
            return new ResponseEntity<>("Product deleted successfully ", HttpStatus.OK);
        }
        return  new ResponseEntity<>("no product to delete", HttpStatus.NOT_FOUND);
    }
}
