package com.WirtualnyMagazynPKR.application.data.productName;

import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.WirtualnyMagazynPKR.application.data.productName.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductNameRepository extends JpaRepository<ProductName,Long> {
    boolean existsByName(String name);
}
