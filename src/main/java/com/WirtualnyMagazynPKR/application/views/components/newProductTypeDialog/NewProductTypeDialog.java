package com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog;

import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog.DialogCancelButton;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog.DialogSaveButton;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog.NewProductTypeLayout;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.confirmpackage.ConfirmNewProductTypeDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@AllArgsConstructor
public class NewProductTypeDialog extends Dialog {

    //  Injected by constructor
    DialogSaveButton dialogSaveButton;
    DialogCancelButton dialogCancelButton;
    TextField productName;
    NewProductTypeLayout layout;
    ConfirmNewProductTypeDialog confirmDialog;


    @Bean
    private void configureProductDialog(){
        dialogCancelButton.setDialog(this);

        this.confirmDialog.setNewProductTypeDialog(this);
        this.dialogSaveButton.setConfirmDialog(confirmDialog);
        this.getFooter().add(dialogSaveButton);
        this.getFooter().add(dialogCancelButton);
        this.setHeaderTitle("Nowy rodzaj produktu");
        this.add(layout);
    }
}
