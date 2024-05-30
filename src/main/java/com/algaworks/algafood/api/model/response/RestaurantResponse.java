package com.algaworks.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantResponse {
	private Long id;
	private String name;
	private BigDecimal freightRate;
	private Boolean status;
	private Boolean open;

	private RestaurantKitchenResponse kitchen;
	private AddressResponse address;
}