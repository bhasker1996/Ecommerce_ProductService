package org.example.ecommerceproductservice.InheritanceTypes.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_Instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String subject;
}
