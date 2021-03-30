package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping
	public List<Restaurant> list() {
		return restaurantRepository.listAll();
	}

	@GetMapping("/{restaurant_id}")
	public Restaurant findById(@PathVariable("restaurant_id") Long id) {
		return restaurantRepository.findById(id);
	}
}