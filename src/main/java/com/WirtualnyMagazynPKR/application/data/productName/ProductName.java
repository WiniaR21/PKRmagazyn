package com.WirtualnyMagazynPKR.application.data.productName;

import com.WirtualnyMagazynPKR.application.data.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Entity(name = "product_name")
@Data
public class ProductName {
    @Id
    @SequenceGenerator(name = "product_name_sequece", sequenceName = "product_name_sequece", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_name_sequece")
    @Column(name = "id", updatable = false)
    Long id;

    @Column(name = "nazwa_produktu", unique = true)
    String name;

    @OneToMany(
            mappedBy = "productName",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )

    @ToString.Exclude
    private List<Product> products = new LinkedList<>();
}
