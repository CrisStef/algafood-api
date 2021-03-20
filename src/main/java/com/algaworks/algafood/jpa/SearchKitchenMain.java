package com.algaworks.algafood.jpa;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class SearchKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		KitchenRegister kitchenRegister = app.getBean(KitchenRegister.class);

		List<Kitchen> kitchens = kitchenRegister.list();

		kitchens.forEach(kitchen -> {
			System.out.println(kitchen.getName());
		});

		Kitchen kitchen = kitchenRegister.search(1L);

		System.out.printf("Busca por Id: %s\n", kitchen.getName());
	}
}