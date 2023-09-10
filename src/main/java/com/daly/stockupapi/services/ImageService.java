package com.daly.stockupapi.services;


import com.daly.stockupapi.entities.Image;
import com.daly.stockupapi.repositories.ImageRepository;
import com.daly.stockupapi.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<String> uploadImage(MultipartFile file) throws IOException {
        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body("Image uploaded successfully: " + file.getOriginalFilename());

    }

    public Image getImageDetails(String name) {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return dbImage.map(image -> Image.builder()
                .name(image.getName())
                .type(image.getType())
                .image(ImageUtility.decompressImage(image.getImage())).build()).orElse(null);

    }

    public ResponseEntity<byte[]> getImage(String name) {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return dbImage.map(image -> ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(ImageUtility.decompressImage(image.getImage()))).orElse(null);

    }

}
