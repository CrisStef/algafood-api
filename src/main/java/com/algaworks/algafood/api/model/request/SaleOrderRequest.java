package com.algaworks.algafood.api.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleOrderRequest {
	@NotNull
	private RestaurantSaleOrderRequest restaurant;

	@NotNull
	private AddressRequest deliveryAddress;

	@NotNull
	private PaymentIdRequest payment;

	@Size(min = 1)
	@NotNull
	private List<SaleOrderItemRequest> itens;
}