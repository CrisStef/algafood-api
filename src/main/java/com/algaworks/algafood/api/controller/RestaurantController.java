package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.RestaurantRequest;
import com.algaworks.algafood.api.model.response.RestaurantResponse;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.KitchenNotFoundException;
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
	public List<RestaurantResponse> findAll() {
		return restaurantService.findAll();
	}

	@GetMapping("/{restaurant_id}")
	public RestaurantResponse getById(@PathVariable("restaurant_id") Long id) {
		RestaurantResponse restaurant = restaurantService.getById(id);

		return restaurant;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantResponse create(@RequestBody @Valid RestaurantRequest restaurant) {
		try {
			return restaurantService.create(restaurant);
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PatchMapping("/{restaurant_id}")
	public RestaurantResponse alter(@RequestBody Map<String, Object> fields, @PathVariable("restaurant_id") Long id, HttpServletRequest request) {
		try {
			return restaurantService.alter(fields, id, request);
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@GetMapping("/find-by-name")
	public List<RestaurantResponse> getByName(String name, Long kitchenId) {
		return restaurantService.getByName(name, kitchenId);
	}

	@GetMapping("/find-by-fraight-rate")
	public List<Restaurant> findByFraightRate(BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		return restaurantService.findByFraightRate(initialFraightRate, finalFraightRate);
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