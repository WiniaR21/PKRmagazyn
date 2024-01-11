package com.WirtualnyMagazynPKR.application.views.components.toolbar.components;

import com.WirtualnyMagazynPKR.application.views.components.toolbar.Toolbar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class FilterText extends TextField{
    //  Inject by setter
    Toolbar toolbar;

    @Bean
    private void configureFilterText(){
       this.setPlaceholder("Szukaj po nazwie...");
       this.setClearButtonVisible(true);
       this.setValueChangeMode(ValueChangeMode.EAGER);
       this.setWidthFull();
       this.addValueChangeListener(e -> toolbar.updateGrid());
   }

}
