package com.algaworks.algafood.api.mapper;

import com.algaworks.algafood.api.model.request.RestaurantRequest;
import com.algaworks.algafood.api.model.response.RestaurantResponse;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
	@Autowired
	private ModelMapper modelMapper;

	public Restaurant restaurantRequestForRestaurant(RestaurantRequest restaurantRequest) {
		return modelMapper.map(restaurantRequest, Restaurant.class);
	}

	public void copyRestaurantRequestForRestaurant(RestaurantRequest restaurantRequest, Restaurant restaurant) {
		restaurant.setKitchen(new Kitchen());
		modelMapper.map(restaurantRequest, restaurant);
	}

	public RestaurantResponse restaurantForRestaurantResponse(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantResponse.class);
	}
}