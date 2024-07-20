package org.example.ecommerceproductservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private String category;
}
