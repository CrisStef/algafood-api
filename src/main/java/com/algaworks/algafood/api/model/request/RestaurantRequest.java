package com.algaworks.algafood.api.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.core.validation.FreightRate;
import com.algaworks.algafood.core.validation.Multiple;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequest {
	@NotBlank
	private String name;

	@NotNull
	@FreightRate
	@Multiple(number = 5)
	private BigDecimal freightRate;

	@Valid
	@NotNull
	private RestaurantKitchenRequest kitchen;

	@Valid
	@NotNull
	private AddressRequest address;
}