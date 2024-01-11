package com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.confirmpackage;


import com.WirtualnyMagazynPKR.application.service.ProductService;
import com.WirtualnyMagazynPKR.application.views.components.content.Content;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.NewProductTypeDialog;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog.ProductName;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.FilterText;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import lombok.AllArgsConstructor;
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
public class ConfirmNewProductTypeDialog extends ConfirmDialog {

    // Inject by setter
    NewProductTypeDialog newProductTypeDialog;

    // Inject by constructor
    private final ProductName productName;
    private final Content content;
    private final FilterText filterText;
    private final ProductService service;

    @Bean
    private void configureNewProductConfirmDialog(){
        this.setHeader("Nowy rodzaj produktu");
        this.setCancelable(true);
        this.setRejectText("Discard");
        this.setConfirmText("Save");
        this.addCancelListener(event -> System.out.println("cancel"));
        this.addRejectListener(event -> System.out.println("Discarded"));
        this.addConfirmListener(event -> addNewProductType());
    }

    public void addNewProductType(){
        boolean success = service.addNewProductType(productName.getValue());
       if (success){
            content.setNewProductTypeDialog(newProductTypeDialog);
            content.setProductName(productName);
            content.refresh();
        }
    }


}
