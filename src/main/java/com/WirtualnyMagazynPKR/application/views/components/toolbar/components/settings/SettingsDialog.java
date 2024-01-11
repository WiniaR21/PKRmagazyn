package com.WirtualnyMagazynPKR.application.views.components.toolbar.components.settings;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SettingsDialog extends Dialog {
    VerticalLayout layout = new VerticalLayout();
    Button manageProductNamesButton = new Button();
    Button manageDepartmentsButton = new Button();
    Button cancelSettingsButton = new Button();

    public SettingsDialog() {
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        layout.getStyle().set("width", "18rem").set("max-width", "100%");
        layout.add(manageProductNamesButton,manageDepartmentsButton);

        manageProductNamesButton.setText("Zarządzaj produktami");
        manageDepartmentsButton.setText("Zarządzaj budynkami");
        cancelSettingsButton.setText("Cofnij");

        addListenersToSettingsDialog();
        this.getFooter().add(cancelSettingsButton);
        this.add(layout);

    }
    private void addListenersToSettingsDialog(){
        cancelSettingsButton.addClickListener(e -> this.close());
    }
}
