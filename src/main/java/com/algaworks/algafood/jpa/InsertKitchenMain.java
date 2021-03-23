package com.algaworks.algafood.jpa;

import java.math.BigDecimal;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

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

        RestaurantRepository restaurantRepository = app.getBean(RestaurantRepository.class);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Gusteau");
        restaurant.setFreightRate(new BigDecimal(6.40));

        restaurantRepository.save(restaurant);
	}
}