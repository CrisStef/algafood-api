package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class RemoveKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		KitchenRegister kitchenRegister = app.getBean(KitchenRegister.class);

		Kitchen kitchen = new Kitchen();
		kitchen.setId(1L);

		kitchenRegister.remove(kitchen);
	}
}