package com.algaworks.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {
	private Long id;
	private String name;
	private CityStateResponse state;
}