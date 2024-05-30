package com.algaworks.algafood.api.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.core.validation.FreightRate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	@NotBlank
	private String name;

	@NotNull
	@FreightRate
	private BigDecimal price;

	@NotBlank
	private String description;

	@NotNull
	private Boolean active;
}