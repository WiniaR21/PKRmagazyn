package com.WirtualnyMagazynPKR.application.data.packaging;

import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.github.javaparser.quality.NotNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@Entity(name = "packaging")
public class Packaging {
    @Id
    @SequenceGenerator(name = "packaging_sequence", sequenceName = "packaging_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "packaging_sequence")
    @Column(name = "id", updatable = false)
    Long id;

    @Column(name = "nazwa_opakowania", nullable = false)
    String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "packaging", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products = new LinkedList<>();
}
