package org.example.ecommerceproductservice.InheritanceTypes.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_Mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private String company;
}
