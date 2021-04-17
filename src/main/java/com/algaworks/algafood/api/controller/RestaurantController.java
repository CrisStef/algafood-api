package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public List<Restaurant> listAll() {
		return restaurantService.listAll();
	}

	@GetMapping("/{restaurant_id}")
	public Restaurant findById(@PathVariable("restaurant_id") Long id) {
		Restaurant restaurant = restaurantService.findById(id);

		return restaurant;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant create(@RequestBody Restaurant restaurant) {
		restaurant = restaurantService.create(restaurant);

		return restaurant;
	}

	@PatchMapping("/{restaurant_id}")
	public Restaurant update(@RequestBody Map<String, Object> fields, @PathVariable("restaurant_id") Long id) {
		Restaurant restaurant = restaurantService.update(fields, id);

		return restaurant;
	}

	@GetMapping("/find-by-fraight-rate")
	public List<Restaurant> findByFraightRate(BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		return restaurantService.findByFraightRate(initialFraightRate, finalFraightRate);
	}

	@GetMapping("/find-by-name")
	public List<Restaurant> findByName(String name, Long kitchenId) {
		return restaurantService.findByName(name, kitchenId);
	}

	@GetMapping("/find")
	public List<Restaurant> find(String name, BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		return restaurantService.find(name, initialFraightRate, finalFraightRate);
	}

	@GetMapping("/find-filter")
	public List<Restaurant> findFilter(String name) {
		return restaurantService.findRestaurantFilter(name);
	}

	@GetMapping("/find-first")
	public Restaurant findFirst() {
		return restaurantService.findRestaurantFirst();
	}
}