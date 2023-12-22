package com.algaworks.algafood.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressRequest {
	@NotBlank
	private String zipCode;

	@NotBlank
	private String publicPlace;

	@NotBlank
	private String number;

	private String complement;

	@NotBlank
	private String district;

	@Valid
	@NotNull
	private AddressCityRequest city;
}