package com.daly.stockupapi.services;

import com.daly.stockupapi.entities.Product;
import com.daly.stockupapi.repositories.ProductRepository;
import com.daly.stockupapi.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public ResponseEntity<String> addProduct(String name, MultipartFile image, double price, int quantity) throws IOException {
        Product newProduct = Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .image(ImageUtility.compressImage(image.getBytes()))
                .imageType(image.getContentType())
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<>("Product created successfully ", HttpStatus.OK);
    }

    public ResponseEntity<String> updateProduct(String id, String name, MultipartFile image, double price, int quantity) throws IOException {
        Optional<Product> toUpdateProduct = productRepository.findById(id);

        if (toUpdateProduct.isPresent()) {
            Product updatedProduct = toUpdateProduct.get();
            updatedProduct.setName(name);
            updatedProduct.setPrice(price);
            updatedProduct.setQuantity(quantity);
            updatedProduct.setImage(ImageUtility.compressImage(image.getBytes()));

            productRepository.save(updatedProduct);

            return new ResponseEntity<>("Product updated successfully ", HttpStatus.OK);

        }
        return new ResponseEntity<>("no product to update ", HttpStatus.NOT_FOUND);

    }


    public ResponseEntity<String> deleteProduct(String id) {
        Optional<Product> toDeleteProduct = productRepository.findById(id);

        if (toDeleteProduct.isPresent()) {
            productRepository.delete(toDeleteProduct.get());
            return new ResponseEntity<>("Product deleted successfully ", HttpStatus.OK);
        }
        return new ResponseEntity<>("no product to delete", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<byte[]> getProductImage(String name) {

        Optional<Product> product = productRepository.findByName(name);

        return product.map(p -> ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(p.getImageType()))
                .body(ImageUtility.decompressImage(p.getImage()))
        ).orElse(null);

    }
}
