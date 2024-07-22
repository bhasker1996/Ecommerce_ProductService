package org.example.ecommerceproductservice.InheritanceTypes.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_Student")
@DiscriminatorValue(value = "1")
public class Student extends User {
    private String batch;
}
