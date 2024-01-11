package com.WirtualnyMagazynPKR.application.views;

import com.WirtualnyMagazynPKR.application.service.ProductService;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.settings.SettingsDialog;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.Toolbar;
import com.WirtualnyMagazynPKR.application.views.components.content.Content;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.confirmpackage.ConfirmNewProductTypeDialog;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.NewProductTypeDialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@PageTitle("Produkty | Polska Korporacja Recyklingu")
@Route("")
@Getter
@Setter
public class MainView extends VerticalLayout {


    public MainView(Test test) {
        this.setSizeFull();

            test.getToolbar().getElement().getNode().removeFromTree();
            test.getContent().getElement().getNode().removeFromTree();

        add(test.getToolbar(), test.getContent());
        test.getContent().updateGrid();
    }
}








