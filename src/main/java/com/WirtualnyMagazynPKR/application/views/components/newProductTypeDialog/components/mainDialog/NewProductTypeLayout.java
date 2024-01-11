package com.WirtualnyMagazynPKR.application.views.components.newProductTypeDialog.components.mainDialog;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
public class NewProductTypeLayout extends VerticalLayout {

    private final ProductName productName;

    @Bean
    private void configureProductName(){
        this.setPadding(false);
        this.setSpacing(false);
        this.setAlignItems(FlexComponent.Alignment.STRETCH);
        this.getStyle().set("width", "18rem").set("max-width", "100%");
        this.add(productName);
    }
}
