package com.daly.stockupapi.repositories;



import com.daly.stockupapi.entities.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {
	Optional<Image> findByName(String name);
}
