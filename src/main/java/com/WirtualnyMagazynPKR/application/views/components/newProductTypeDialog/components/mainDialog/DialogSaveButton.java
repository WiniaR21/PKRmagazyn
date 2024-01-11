package com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog;

import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.confirmpackage.ConfirmNewProductTypeDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@RequiredArgsConstructor
public class DialogSaveButton extends Button {
    //  Injected by setter
    ConfirmNewProductTypeDialog confirmDialog;

    //  Inject by constructor
    private final ProductName productName;


    @Bean
    private void configureDialogSaveButton(){
        this.setText("Save");
        this.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        this.addClickListener(e -> saveNewProduct(confirmDialog, productName));
    }

    public void saveNewProduct(ConfirmNewProductTypeDialog confirmDialog, ProductName productName){
        confirmDialog.setText("Napewno chcesz dodać rodzaj produktu " + productName.getValue() + "?");
        confirmDialog.open();
    }

}
