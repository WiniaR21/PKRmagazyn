package com.WirtualnyMagazynPKR.application.data.department;

import com.WirtualnyMagazynPKR.application.data.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "department")
@Data
public class Department {

    @Id
    @SequenceGenerator(name = "department_sequence", sequenceName = "department_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "department_sequence")
    @Column(name = "id", updatable = false)
    Long id;

    @Column(name = "nazwa_magazynu", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "department", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products = new LinkedList<>();

    public void addProduct(Product product){
        products.add(product);
    }

}
