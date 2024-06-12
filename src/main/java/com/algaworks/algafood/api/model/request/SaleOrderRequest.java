package com.algaworks.algafood.api.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleOrderRequest {
	@Valid
	@NotNull
	private RestaurantSaleOrderRequest restaurant;

	@Valid
	@NotNull
	private AddressRequest deliveryAddress;

	@Valid
	@NotNull
	private PaymentIdRequest payment;

	@Valid
	@Size(min = 1)
	@NotNull
	private List<SaleOrderItemRequest> itens;
}