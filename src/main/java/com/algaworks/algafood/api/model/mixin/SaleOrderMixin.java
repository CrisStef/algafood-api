package com.algaworks.algafood.api.model.mixin;

import java.time.LocalDateTime;

import com.algaworks.algafood.domain.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SaleOrderMixin {
	@JsonIgnore
	private LocalDateTime registrationDate;

	@JsonIgnore
	private Address deliveryAddress;
}
