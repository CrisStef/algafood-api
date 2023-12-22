package com.algaworks.algafood.api.mapper;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.api.model.request.RestaurantRequest;
import com.algaworks.algafood.api.model.response.RestaurantResponse;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.TypeToken;

@Component
public class RestaurantMapper {
	@Autowired
	private ModelMapper modelMapper;

	public Restaurant restaurantRequestForRestaurant(RestaurantRequest restaurantRequest) {
		return modelMapper.map(restaurantRequest, Restaurant.class);
	}

	public void copyRestaurantRequestForRestaurant(RestaurantRequest restaurantRequest, Restaurant restaurant) {
		restaurant.setKitchen(new Kitchen());
		Optional.ofNullable(restaurant.getAddress())
				.ifPresent(address -> address.setCity(new City()));
		modelMapper.map(restaurantRequest, restaurant);
	}

	public RestaurantResponse restaurantForRestaurantResponse(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantResponse.class);
	}

	public List<RestaurantResponse> restaurantListForRestaurantListResponse(List<Restaurant> restaurant) {
		return modelMapper.map(restaurant, new TypeToken<List<RestaurantResponse>>(){}.getType());
	}
}