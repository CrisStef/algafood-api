package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InsertKitchenMain {
	
	public static void main(String[] args) {
		ApplicationContext app = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
        KitchenRepository kitchenRepository = app.getBean(KitchenRepository.class);

		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Brasileira");

		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japonesa");
		
		kitchenRepository.save(kitchen1);
		kitchenRepository.save(kitchen2);
	}
}