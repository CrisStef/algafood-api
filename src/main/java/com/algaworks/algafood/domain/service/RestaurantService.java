package com.algaworks.algafood.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.algaworks.algafood.api.mapper.RestaurantMapper;
import com.algaworks.algafood.api.model.request.RestaurantRequest;
import com.algaworks.algafood.api.model.response.RestaurantResponse;
import com.algaworks.algafood.domain.exception.RestaurantNotFoundException;
import com.algaworks.algafood.domain.exception.ValidatorException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.util.MergeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private SmartValidator validator;

	@Autowired 
	private RestaurantMapper restaurantMapper;

	public RestaurantResponse create(RestaurantRequest restaurantRequest) {
		Restaurant restaurant = restaurantMapper.restaurantRequestForRestaurant(restaurantRequest);

		return restaurantMapper.restaurantForRestaurantResponse(this.save(restaurant));
	}

	@Transactional
	private Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenService.findById(kitchenId);

		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	public Restaurant findById(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));

		return restaurant;
	}

	public List<Restaurant> listAll() {
		return restaurantRepository.findAll();
	}

	@Transactional
	public Restaurant update(Map<String, Object> restaurant, Long id, HttpServletRequest request) {
		Restaurant currentRestaurant = findById(id);

		MergeMapper.merge(restaurant, currentRestaurant, request);

		validate(currentRestaurant, "restaurant");

		Long kitchenId = currentRestaurant.getKitchen().getId();

		Kitchen kitchen = kitchenService.findById(kitchenId);

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

	public Restaurant findRestaurantFirst() {
		return restaurantRepository.findFirst().get();
	}

	private void validate(Restaurant restaurant, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
		validator.validate(restaurant, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidatorException(bindingResult);
		}
	}
}