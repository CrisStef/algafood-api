package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.api.model.mixin.CityMixin;
import com.algaworks.algafood.api.model.mixin.ClusterMixin;
import com.algaworks.algafood.api.model.mixin.KitchenMixin;
import com.algaworks.algafood.api.model.mixin.ProductMixin;
import com.algaworks.algafood.api.model.mixin.RestaurantMixin;
import com.algaworks.algafood.api.model.mixin.SaleOrderMixin;
import com.algaworks.algafood.api.model.mixin.UserMixin;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.Cluster;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.model.SaleOrder;
import com.algaworks.algafood.domain.model.User;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
		setMixInAnnotation(City.class, CityMixin.class);
		setMixInAnnotation(Cluster.class, ClusterMixin.class);
		setMixInAnnotation(Kitchen.class, KitchenMixin.class);
		setMixInAnnotation(Product.class, ProductMixin.class);
		setMixInAnnotation(SaleOrder.class, SaleOrderMixin.class);
		setMixInAnnotation(User.class, UserMixin.class);
	}
}
