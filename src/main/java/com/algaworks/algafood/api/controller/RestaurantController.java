package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Map;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
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
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public List<Restaurant> list() {
		return restaurantRepository.listAll();
	}

	@GetMapping("/{restaurant_id}")
	public Restaurant findById(@PathVariable("restaurant_id") Long id) {
		return restaurantRepository.findById(id);
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
}