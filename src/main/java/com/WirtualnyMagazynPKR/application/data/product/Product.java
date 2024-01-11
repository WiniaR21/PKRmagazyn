package com.WirtualnyMagazynPKR.application.data.product;


import com.WirtualnyMagazynPKR.application.data.department.Department;
import com.WirtualnyMagazynPKR.application.data.packaging.Packaging;
import com.WirtualnyMagazynPKR.application.data.productName.ProductName;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "product_sequence")
    @Column(name = "product_id", updatable = false)
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "product_name_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "product_name_fk"))
    private ProductName productName;

    @ManyToOne()
    @JoinColumn(name = "pacgaging_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "packaging_fk"))
    private Packaging packaging;

    @Column(name = "waga_netto")
    private Double netWeight;

    @Column(name = "waga_brutto")
    private Double grossWeight;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "product_departament_fk"))
    private Department department;

}
