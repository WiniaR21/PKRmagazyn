package com.WirtualnyMagazynPKR.application.service;

import com.WirtualnyMagazynPKR.application.data.department.Department;
import com.WirtualnyMagazynPKR.application.data.packaging.Packaging;
import com.WirtualnyMagazynPKR.application.data.packaging.PackagingRepository;
import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.WirtualnyMagazynPKR.application.data.productName.ProductName;
import com.WirtualnyMagazynPKR.application.data.department.DepartmentRepository;
import com.WirtualnyMagazynPKR.application.data.productName.ProductNameRepository;
import com.WirtualnyMagazynPKR.application.data.product.ProductRepository;
import com.vaadin.flow.component.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final DepartmentRepository departmentRepository;
    private final ProductNameRepository productNameRepository;
    private final PackagingRepository packagingRepository;
    private final ProductNotification productNotification;

        // Search in DB
    public List<Product> findAllProducts(String filterText){
        if(filterText == null || filterText.isEmpty()){
                return productRepository.findAll();
            }else{
            return productRepository.searchByProductName_Name(filterText);
            }
    }
    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }
    public List<Packaging> findAllPackagings() {
        return packagingRepository.findAll();
    }
    public List<ProductName> findAllProductNames() {
        return productNameRepository.findAll();
    }


        //  Transactional
    @Transactional
    public void saveAllDepartaments(List<Department> departments){
        departmentRepository.saveAll(departments);
    }
    @Transactional
    public void saveAllProductNames(List<ProductName> productNames){
        productNameRepository.saveAll(productNames);
    }
    @Transactional
    public void saveAllPackagings(List<Packaging> packagings){
        packagingRepository.saveAll(packagings);
    }
    @Transactional()
    public boolean saveProduct(Product product){
        if (product.getProductName() == null ||
                product.getPackaging() == null ||
                product.getDepartment() == null ||
                product.getNetWeight() == null ||
                product.getGrossWeight() == null
        )
        {

            productNotification.showInvalidSaveNotification();
            return false;
        }
        if (product.getNetWeight() > product.getGrossWeight()){
            productNotification.showInvalidWeight();
            return false;
        }




        if(product.getId() != null){
            if (productRepository.existsById(product.getId())){
                productRepository.save(product);
                productNotification.showSuccessUpdateNotification();
                return true;
            }
        }else {
            productRepository.save(product);
            productNotification.showSuccessSaveNotification(product);
            return true;
        }
        return false;
    }
    @Transactional
    public boolean addNewProductType(String name) {
        if (name.isEmpty()){
            productNotification.showNameIsInvalid();
            return false;
        }

        ProductName productName = new ProductName();
        productName.setName(name);

        if(productNameRepository.existsByName(name)){
            productNotification.showThisTypeExist(name);
            return false;
        }

        productNameRepository.save(productName);
        productNotification.showSuccessNewProductTypeAdded(productName);
        return true;
    }
    @Transactional
    public void addProduct(Product product, Department department) {
        departmentRepository
                .findByName(department.getName())
                .ifPresent(department1 -> department1.addProduct(product));
    }
    @Transactional
    public boolean deleteProduct(Product product){
        if (product.getId() == null) {
            productNotification.showInvalidDeleteNotification();
            return false;
        }

        // Remove dependencies
        Department department = product.getDepartment();
        if (department != null) {
            department.getProducts().remove(product);
            departmentRepository.save(department);
        }
        ProductName productName = product.getProductName();
        if (productName != null) {
            productName.getProducts().remove(product);
            productNameRepository.save(productName);
        }
        Packaging packaging = product.getPackaging();
        if (packaging != null) {
            packaging.getProducts().remove(product);
            packagingRepository.save(packaging);
        }
        System.out.println(product);
        // Remove product
        productRepository.deleteById(product.getId());
        productNotification.showSuccessDeleteNotification(product);
        return true;
    }
}
