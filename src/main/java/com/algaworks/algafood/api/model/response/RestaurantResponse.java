package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse {
	private Long id;
	private String name;
	private BigDecimal freightRate;
	private Boolean status;

	private RestaurantKitchenResponse kitchen;
}