package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InsertKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		KitchenRegister kitchenRegister = app.getBean(KitchenRegister.class);

		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Brasileira");

		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japonesa");
		
		kitchenRegister.save(kitchen1);
		kitchenRegister.save(kitchen2);
	}
}