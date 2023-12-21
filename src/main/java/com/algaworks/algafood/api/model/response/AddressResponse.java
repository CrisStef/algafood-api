package com.algaworks.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
	private String zipCode;
	private String publicPlace;
	private String number;
	private String complement;
	private String district;
	private AddressCityResponse city;
}