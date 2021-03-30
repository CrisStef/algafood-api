package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenRepository kitchenRepository;

	public Restaurant create(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId);

		if (kitchen == null) {
			throw new EntityNotFoundException(String.format("Kitchen not found! Id: %d", kitchenId));
		}

		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	public Restaurant update(Restaurant restaurant, Long id) {
		Long kitchenId = restaurant.getKitchen().getId();

		Kitchen kitchen = kitchenRepository.findById(kitchenId);

		Restaurant currentRestaurant = restaurantRepository.findById(id);

		if (currentRestaurant == null) {
			throw new EntityNotFoundException(String.format("Restaurant not found! Id: %d", id));
		}
		if (kitchen == null) {
			throw new RuntimeException(String.format("Kitchen not found! Id: %d", kitchenId));
		}

		BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
		currentRestaurant.setKitchen(kitchen);

		return restaurantRepository.save(currentRestaurant);
	}
}