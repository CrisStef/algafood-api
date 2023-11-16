package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.api.model.mixin.CityMixin;
import com.algaworks.algafood.api.model.mixin.RestaurantMixin;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.Restaurant;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
		setMixInAnnotation(City.class, CityMixin.class);
	}
}
