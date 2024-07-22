package org.example.ecommerceproductservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.ecommerceproductservice.Models.Category;

@Getter
@Setter
public class ProductRequestDTO {
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private Category category;
}
