package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.RestaurantRequest;
import com.algaworks.algafood.api.model.response.RestaurantResponse;
import com.algaworks.algafood.api.model.view.RestaurantView;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.CityNotFoundException;
import com.algaworks.algafood.domain.exception.KitchenNotFoundException;
import com.algaworks.algafood.domain.exception.RestaurantNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;

	// @GetMapping
	// public List<RestaurantResponse> findAll() {
	// 	return restaurantService.findAll();
	// }

	@GetMapping
	public MappingJacksonValue findAll(@RequestParam(required = false) String projection) {
		List<RestaurantResponse> restaurantResponse = restaurantService.findAll();

		MappingJacksonValue restaurantWrapper = new MappingJacksonValue(restaurantResponse);

		restaurantWrapper.setSerializationView(RestaurantView.RestauranteSummary.class);

		if ("name-only".equals(projection)) {
			restaurantWrapper.setSerializationView(RestaurantView.NameOnly.class);
		} else if ("all".equals(projection)) {
			restaurantWrapper.setSerializationView(null);
		}

		return restaurantWrapper;
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
		} catch (KitchenNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PatchMapping("/{restaurant_id}")
	public RestaurantResponse alter(@RequestBody Map<String, Object> fields, @PathVariable("restaurant_id") Long id, HttpServletRequest request) {
		try {
			return restaurantService.alter(fields, id, request);
		} catch (KitchenNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restaurant_id}/activate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activateRestaurant(@PathVariable("restaurant_id") Long id) {
		restaurantService.activateRestaurant(id);
	}

	@DeleteMapping("/{restaurant_id}/activate")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disableRestaurant(@PathVariable("restaurant_id") Long id) {
		restaurantService.disableRestaurant(id);
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

	@PutMapping("/{restaurant_id}/open")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void openRestaurant(@PathVariable("restaurant_id") Long id) {
		restaurantService.openRestaurant(id);
	}

	@PutMapping("/{restaurant_id}/closed")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void closedRestaurant(@PathVariable("restaurant_id") Long id) {
		restaurantService.closedRestaurant(id);
	}

	@PutMapping("/activations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activationsRestaurants(@RequestBody List<Long> ids) {
		try {
			restaurantService.activationsRestaurants(ids);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/disables")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disablesRestaurants(@RequestBody List<Long> id) {
		try {
			restaurantService.disablesRestaurants(id);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
}