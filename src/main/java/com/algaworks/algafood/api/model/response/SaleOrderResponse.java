package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.algaworks.algafood.domain.model.enums.SaleOrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleOrderResponse {
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal freightRate;
	private BigDecimal totalValue;
	private OffsetDateTime registrationDate;
	private OffsetDateTime confirmationDate;
	private OffsetDateTime cancellationDate;
	private OffsetDateTime deliveryDate;
	private AddressResponse deliveryAddress;
	private PaymentResponse payment;
	private SaleOrderStatus saleOrderStatus;
	private RestaurantSaleOrderResponse restaurant;
	private UserResponse customer;
	private List<RestaurantSaleOrderItemResponse> itens;
}