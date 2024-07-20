package org.example.ecommerceproductservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ECOM_PRODUCT")
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    @ManyToOne
    private Category category;
    private String imageURL;

}
