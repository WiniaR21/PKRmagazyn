package com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog;

import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.NewProductTypeDialog;
import com.vaadin.flow.component.button.Button;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
public class DialogCancelButton extends Button {
    //  Injected by setter
    NewProductTypeDialog dialog;
    public DialogCancelButton() {
        this.setText("Cancel");
        this.addClickListener(e -> dialog.close());
    }
}
