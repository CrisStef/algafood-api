package com.algaworks.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import com.algaworks.algafood.domain.model.Restaurant;

import org.springframework.data.jpa.domain.Specification;

public class RestaurantSpecs {
	public static Specification<Restaurant> freeFreight() {
		return (root, query, builder) -> builder.equal(root.get("freightRate"), BigDecimal.ZERO);
	}

	public static Specification<Restaurant> similarName(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
	}
}