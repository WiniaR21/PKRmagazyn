package com.WirtualnyMagazynPKR.application.data.product;

import com.WirtualnyMagazynPKR.application.data.department.Department;
import com.WirtualnyMagazynPKR.application.data.packaging.Packaging;
import com.WirtualnyMagazynPKR.application.data.productName.ProductName;
import com.WirtualnyMagazynPKR.application.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestDataConfiguration {
    Department trzynastka = new Department();
    Department pietnastka = new Department();

    ProductName regranulat = new ProductName();
    ProductName psabs = new ProductName();
    ProductName lodowkaLekka = new ProductName();
    ProductName lodowkaCiezka = new ProductName();

    Packaging bigbag = new Packaging();
    Packaging kontener = new Packaging();



    @Bean
    CommandLineRunner commandLineRunner(ProductService service){

        return args -> {
            load_static_data(service);



            for (int i=0; i<4; i++){
                Product product = new Product();
                product.setPackaging(bigbag);
                product.setProductName(regranulat);
                product.setNetWeight(20.0);
                product.setGrossWeight(30.0);
                product.setDepartment(trzynastka);
                service.addProduct(product,trzynastka);
            }
            Product product = new Product();
            product.setPackaging(kontener);
            product.setProductName(psabs);
            product.setNetWeight(20.0);
            product.setGrossWeight(30.0);
            product.setDepartment(pietnastka);
            service.addProduct(product,pietnastka);
        };
    }

    private void load_static_data(ProductService service){
        trzynastka.setName("Metalurgiczna 13");
        pietnastka.setName("Metalurgiczna 15C");
                regranulat.setName("Regranulat");
                psabs.setName("PS/ABS");
                lodowkaLekka.setName("Lodówka lekka");
                lodowkaCiezka.setName("Lodówka ciężka");
                    bigbag.setName("BigBag");
                    kontener.setName("Kontener");

                service.saveAllDepartaments(List.of(trzynastka,pietnastka));
                service.saveAllProductNames(List.of(regranulat,psabs,lodowkaLekka,lodowkaCiezka));
                service.saveAllPackagings(List.of(bigbag,kontener));
    }
}
