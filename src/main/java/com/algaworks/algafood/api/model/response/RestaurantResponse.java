package com.algaworks.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.algaworks.algafood.api.model.view.RestaurantView;
import com.fasterxml.jackson.annotation.JsonView;

@Getter
@Setter
public class RestaurantResponse {
	@JsonView( { RestaurantView.RestauranteSummary.class, RestaurantView.NameOnly.class})
	private Long id;

	@JsonView( { RestaurantView.RestauranteSummary.class, RestaurantView.NameOnly.class})
	private String name;

	@JsonView(RestaurantView.RestauranteSummary.class)
	private BigDecimal freightRate;

	@JsonView(RestaurantView.RestauranteSummary.class)
	private Boolean status;

	@JsonView(RestaurantView.RestauranteSummary.class)
	private Boolean open;

	@JsonView(RestaurantView.RestauranteSummary.class)
	private RestaurantKitchenResponse kitchen;

	private AddressResponse address;
}