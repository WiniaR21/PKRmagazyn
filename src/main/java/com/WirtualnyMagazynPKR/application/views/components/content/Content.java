package com.WirtualnyMagazynPKR.application.views.components.content;


import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.WirtualnyMagazynPKR.application.service.ProductService;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.NewProductTypeDialog;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog.ProductName;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.FilterText;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Content extends HorizontalLayout {

    // Injected by setter
    NewProductTypeDialog newProductTypeDialog;
    ProductName productName;

    ProductForm form;
    Grid<Product> grid = new Grid<>(Product.class);


    // Injected by constructor
    FilterText filterText;
    private final ProductService service;

    @Autowired
    public Content(ProductService service, FilterText filterText) {
        this.service = service;
        this.filterText = filterText;

        this.addClassName("product-grid");

        grid.setColumns("netWeight", "grossWeight");
        grid.getColumnByKey("netWeight").setHeader("Waga netto");
        grid.getColumnByKey("grossWeight").setHeader("Waga brutto");
        grid.addColumn(product -> product.getPackaging().getName()).setHeader("Opakowanie");
        grid.addColumn(product -> product.getDepartment().getName()).setHeader("Budynek");
        grid.addColumn(product -> product.getProductName().getName()).setHeader("Nazwa produktu");
        grid.getColumns().forEach(productColumn -> productColumn.setAutoWidth(true));
        grid.setSizeFull();

        configureForm();
        addListenersToGrid();
        addListenersToForm();

        this.addClassName("content");
        this.setSizeFull();
        this.add(grid,form);

    }
    public void configureForm(){
        rebuildForm();
    }
    private void rebuildForm(){
        form = new ProductForm(
                service.findAllDepartments(),
                service.findAllProductNames(),
                service.findAllPackagings()
        );
        closeEditor();
    }
    public void closeEditor() {
        form.setProduct(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    private void addListenersToGrid(){
        grid.asSingleSelect().addValueChangeListener(e -> editProduct(e.getValue()));
    }
    private void addListenersToForm(){
        form.addListener(ProductForm.SaveEvent.class, this::trySaveProduct);
        form.addListener(ProductForm.DeleteEvent.class, this::deleteProduct);
        form.addListener(ProductForm.CloseEvent.class, e -> this.closeEditor());
    }
    private void editProduct(Product product) {
        this.getForm().setProduct(product);
        this.getForm().setVisible(true);
        addClassName("editing");
    }
    private void trySaveProduct(ProductForm.SaveEvent event){
        boolean success = service.saveProduct(event.getProduct());
        if(success){
            updateGrid();
            this.closeEditor();
        }
    }
    public void updateGrid()
    {grid.setItems(service.findAllProducts(filterText.getValue()));}
    private void deleteProduct(ProductForm.DeleteEvent event){
        boolean success = service.deleteProduct(event.getProduct());
        updateGrid();
        if(success){
            this.closeEditor();
        }
    }

    public void refresh(){
        this.remove(form);
        configureForm();
        form.addListener(ProductForm.SaveEvent.class, this::trySaveProduct);
        form.addListener(ProductForm.DeleteEvent.class, this::deleteProduct);
        form.addListener(ProductForm.CloseEvent.class, e -> closeEditor());
        this.add(form);

        newProductTypeDialog.close();
        productName.clear();
    }
}
