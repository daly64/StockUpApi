package com.daly.stockupapi.repositories;

import com.daly.stockupapi.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
