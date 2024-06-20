package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algafood.domain.model.enums.SaleOrderStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SaleOrderListResponse {
	private String code;
	private BigDecimal subtotal;
	private BigDecimal freightRate;
	private BigDecimal totalValue;
	private SaleOrderStatus saleOrderStatus;
	private OffsetDateTime registrationDate;
	private RestaurantSaleOrderResponse restaurant;
	private UserResponse customer;
}