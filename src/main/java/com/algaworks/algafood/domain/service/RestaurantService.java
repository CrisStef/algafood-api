package com.algaworks.algafood.domain.service;

import java.util.Map;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.util.MergeMapper;

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
		Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() -> new RuntimeException(String.format("Kitchen not found! Id: %d", kitchenId)));

		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	public Restaurant update(Map<String, Object> restaurant, Long id) {
		Restaurant currentRestaurant = restaurantRepository.findById(id);

		if (currentRestaurant == null) {
			throw new EntityNotFoundException(String.format("Restaurant not found! Id: %d", id));
		}

		MergeMapper.merge(restaurant, currentRestaurant);

		Long kitchenId = currentRestaurant.getKitchen().getId();

		Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() -> new RuntimeException(String.format("Kitchen not found! Id: %d", kitchenId)));

		currentRestaurant.setKitchen(kitchen);

		return restaurantRepository.save(currentRestaurant);
	}
}