package com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog;

import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Component;

@Component
public class ProductName extends TextField {
    public ProductName() {
        this.setPlaceholder("Nazwa");
    }

}
