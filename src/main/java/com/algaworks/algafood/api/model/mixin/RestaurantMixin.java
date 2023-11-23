package com.algaworks.algafood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.Address;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Payment;
import com.algaworks.algafood.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestaurantMixin {
	@JsonIgnoreProperties(value = "name", allowGetters = true)
	private Kitchen kitchen;

	@JsonIgnore
	private Address address;

	@JsonIgnore
	private OffsetDateTime registrationDate;

	@JsonIgnore
	private OffsetDateTime updateDate;

	@JsonIgnore
	private List<Payment> payments = new ArrayList<>();

	@JsonIgnore
	private List<Product> products = new ArrayList<>();
}
