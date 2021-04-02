package com.algaworks.algafood.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() -> new EntityNotFoundException(String.format("Kitchen not found! Id: %d", kitchenId)));

		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	public Restaurant findById(Long id) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);

		if (!restaurant.isPresent()) {
			throw new EntityNotFoundException(String.format("Restaurant not found! Id: %d", id));
		}

		return restaurant.get();
	}

	public List<Restaurant> listAll() {
		return restaurantRepository.findAll();
	}

	public Restaurant update(Map<String, Object> restaurant, Long id) {
		Restaurant currentRestaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Restaurant not found! Id: %d", id)));

		MergeMapper.merge(restaurant, currentRestaurant);

		Long kitchenId = currentRestaurant.getKitchen().getId();

		Kitchen kitchen = kitchenRepository.findById(kitchenId).orElseThrow(() -> new RuntimeException(String.format("Kitchen not found! Id: %d", kitchenId)));

		currentRestaurant.setKitchen(kitchen);

		return restaurantRepository.save(currentRestaurant);
	}

	public List<Restaurant> findByFraightRate(BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		return restaurantRepository.findByFreightRateBetween(initialFraightRate, finalFraightRate);
	}

	public List<Restaurant> findByName(String name, Long kitchenId) {
		return restaurantRepository.findByNameAndKitchenId(name, kitchenId);
	}

	public List<Restaurant> find(String name, BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		return restaurantRepository.find(name, initialFraightRate, finalFraightRate);
	}

	public List<Restaurant> findRestaurantFilter(String name) {
		return restaurantRepository.findRestaurantFilter(name);
	}
}