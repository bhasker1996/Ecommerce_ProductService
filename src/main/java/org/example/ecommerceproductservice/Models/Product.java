package org.example.ecommerceproductservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable{
    private String title;
    private Double price;
    private String description;
    //@JsonIgnoreProperties("productList")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    private String imageURL;
}
