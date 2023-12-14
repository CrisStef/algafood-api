package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductMixin {
	@JsonIgnore
	private Restaurant restaurant;
}
