package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<?> findById(@PathVariable("restaurant_id") Long id) {
		try {
			Restaurant restaurant = restaurantService.findById(id);
			return ResponseEntity.ok().body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Restaurant restaurant) {
		try {
			restaurant = restaurantService.create(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/{restaurant_id}")
	public ResponseEntity<?> update(@RequestBody Map<String, Object> fields, @PathVariable("restaurant_id") Long id) {
		try {
			Restaurant restaurant = restaurantService.update(fields, id);
			return ResponseEntity.ok().body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
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
}