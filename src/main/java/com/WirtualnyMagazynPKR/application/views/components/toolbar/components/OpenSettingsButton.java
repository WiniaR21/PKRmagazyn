package com.WirtualnyMagazynPKR.application.views.components.toolbar.components;

import com.WirtualnyMagazynPKR.application.views.components.toolbar.Toolbar;
import com.vaadin.flow.component.button.Button;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class OpenSettingsButton extends Button {

    //  Inject By setter
    Toolbar toolbar;

    public OpenSettingsButton() {
        this.setText("Ustawienia");
        this.addClickListener(e -> toolbar.getSettingsDialog().open());
    }
}
