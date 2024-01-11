package com.WirtualnyMagazynPKR.application.views;

import com.WirtualnyMagazynPKR.application.service.ProductService;
import com.WirtualnyMagazynPKR.application.views.components.content.Content;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.NewProductTypeDialog;
import com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.confirmpackage.ConfirmNewProductTypeDialog;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.Toolbar;
import com.WirtualnyMagazynPKR.application.views.components.toolbar.components.settings.SettingsDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
public class Test extends HorizontalLayout {

    private final Toolbar toolbar;
    private final SettingsDialog settingsDialog;
    private final Content content;
    private final ConfirmNewProductTypeDialog confirmNewProductTypeDialog;
    private final NewProductTypeDialog newProductTypeDialog;
    private final ProductService service;

}
