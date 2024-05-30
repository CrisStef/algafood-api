package com.algaworks.algafood.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
	private Long id;
	private String name;
	private BigDecimal price;
	private String description;
	private Boolean active;
}