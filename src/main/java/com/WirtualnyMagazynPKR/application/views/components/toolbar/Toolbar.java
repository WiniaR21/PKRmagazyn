package com.WirtualnyMagazynPKR.application.views.components.toolbar;

import com.WirtualnyMagazynPKR.application.data.product.Product;
import com.WirtualnyMagazynPKR.application.service.ProductService;
import com.WirtualnyMagazynPKR.application.views.components.content.Content;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.NewProductTypeDialog;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.settings.SettingsDialog;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.AddNewProductTypeButton;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.AddProductButton;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.FilterText;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.OpenSettingsButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@AllArgsConstructor
public class Toolbar extends HorizontalLayout {



    //  Injected by constructor
    FilterText filterText;
    AddProductButton addProductButton;
    AddNewProductTypeButton addNewProductTypeButton;
    OpenSettingsButton openSettingsButton;
    NewProductTypeDialog newProductTypeDialog;
    SettingsDialog settingsDialog;
    Content content;
    ProductService service;

    @Bean
    private void configureToolbar(){
        this.addClassName("toolbar");
        this.add(filterText, addProductButton, addNewProductTypeButton, openSettingsButton);

        addProductButton.setToolbar(this);
        addNewProductTypeButton.setToolbar(this);
        openSettingsButton.setToolbar(this);
        filterText.setToolbar(this);
    }

    public void addProduct() {
        Product product = new Product();

        content.getGrid().asSingleSelect().clear();
        content.getForm().setProduct(product);
        content.getForm().setVisible(true);
        addClassName("editing");
    }

    public void updateGrid()
    {content.updateGrid();}
}
