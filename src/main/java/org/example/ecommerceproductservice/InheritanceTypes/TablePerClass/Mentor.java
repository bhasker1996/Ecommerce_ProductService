package org.example.ecommerceproductservice.InheritanceTypes.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_Mentor")
public class Mentor extends User {
    private String company;
}
