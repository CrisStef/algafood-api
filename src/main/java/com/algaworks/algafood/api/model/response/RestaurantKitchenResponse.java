package com.algaworks.algafood.api.model.response;

import com.algaworks.algafood.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantKitchenResponse {
	@JsonView(RestaurantView.RestauranteSummary.class)
	private Long id;

	@JsonView(RestaurantView.RestauranteSummary.class)
	private String name;
}