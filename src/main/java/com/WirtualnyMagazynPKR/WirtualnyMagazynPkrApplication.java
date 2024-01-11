package com.WirtualnyMagazynPKR;

import com.vaadin.flow.component.dependency.CssImport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@CssImport("./themes/components/styles.css")
public class WirtualnyMagazynPkrApplication {

	public static void main(String[] args) {
		SpringApplication.run(WirtualnyMagazynPkrApplication.class, args);
	}

}
