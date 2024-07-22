package org.example.ecommerceproductservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
   // @JsonIgnoreProperties("category")
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
