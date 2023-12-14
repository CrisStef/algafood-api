package com.algaworks.algafood.api.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {
	@NotBlank
	private String name;

	@Valid
	@NotNull
	private CityStateRequest state;
}