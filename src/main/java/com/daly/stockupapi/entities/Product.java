package com.daly.stockupapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
    private byte[] photo;
    private String photoType;

}
