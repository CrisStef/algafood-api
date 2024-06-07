package com.algaworks.algafood.api.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleOrderItemRequest {
	@NotNull
	private Long productId;

	@NotNull
	@PositiveOrZero
	private Integer quantity;

	private String observation;
}