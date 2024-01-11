package com.WirtualnyMagazynPKR.application.data.product;

import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.WirtualnyMagazynPKR.application.data.productName.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
   /* @Query("select c from Product c " + " where lower(c.name) like lower(concat('%', :searchTerm, '%')) " )
    List<Product> search(@Param("searchTerm") String filterText);*/
   @Query("SELECT p FROM Product p WHERE lower(p.productName.name) LIKE lower(concat('%', :name, '%'))")
    List<Product> searchByProductName_Name(@Param("name")String name);
}
