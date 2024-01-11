package com.WirtualnyMagazynPKR.application.views.components.content;

import com.WirtualnyMagazynPKR.application.data.department.Department;
import com.WirtualnyMagazynPKR.application.data.packaging.Packaging;
import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.WirtualnyMagazynPKR.application.data.productName.ProductName;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;


public class ProductForm extends FormLayout {
    Binder<Product> binder = new BeanValidationBinder<>(Product.class);
    //TextField name = new TextField("Nazwa produktu");
    TextField netWeight = new TextField("Waga netto");
    TextField grossWeight = new TextField("Waga brutto");

    ComboBox<Packaging> packaging = new ComboBox<>("Opakowanie");
    ComboBox<ProductName> productName = new ComboBox<>("Nazwa produktu");
    ComboBox<Department> department = new ComboBox<>("Budynek");

    Button save = new Button("Zapisz");
    Button delete = new Button("Usuń");
    Button cancel = new Button("Cofnij");

    private Product product;


    public ProductForm(
            List<Department> departments,
            List<ProductName> productNames,
            List<Packaging> packagings
    ){
        addClassName("product-form");
        binder.bindInstanceFields(this);

        department.setItems(departments);
        department.setItemLabelGenerator(Department::getName);

        productName.setItems(productNames);
        productName.setItemLabelGenerator(ProductName::getName);

        packaging.setItems(packagings);
        packaging.setItemLabelGenerator(Packaging::getName);


        add(
                productName,packaging,department,netWeight,grossWeight,
                createButtonLayout()
        );
    }
    public void setProduct(Product product){
        this.product = product;
        binder.readBean(product);
    }
    private Component createButtonLayout(){
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(buttonClickEvent -> fireEvent(new DeleteEvent(this,product)));
        cancel.addClickListener(buttonClickEvent -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(save,delete,cancel);
        buttonLayout.setSizeFull();


        return buttonLayout;
    }

    private void validateAndSave() {
        try {
            binder.writeBean(product);
            fireEvent(new SaveEvent(this, product));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ProductFormEvent extends ComponentEvent<ProductForm> {
        private Product product;

        protected ProductFormEvent(ProductForm source, Product product) {
            super(source, false);
            this.product = product;
        }

        public Product getProduct() {
            return product;
        }
    }

    public static class SaveEvent extends ProductFormEvent {
        SaveEvent(ProductForm source, Product product) {
            super(source, product);
        }
    }

    public static class DeleteEvent extends ProductFormEvent {
        DeleteEvent(ProductForm source, Product product) {
            super(source, product);
        }

    }

    public static class CloseEvent extends ProductFormEvent {
        CloseEvent(ProductForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }
}
