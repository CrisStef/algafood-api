package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.mapper.UserMapper;
import com.algaworks.algafood.api.model.response.UserResponse;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants/{restaurant_id}/users")
public class RestaurantUserController {
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private UserMapper userMapper;

	@GetMapping
	public List<UserResponse> findAll(@PathVariable("restaurant_id") Long restaurantId) {
		Restaurant restaurant = restaurantService.findById(restaurantId);

		return userMapper.userListForUserListResponse(restaurant.getUsers());
	}

	@DeleteMapping("/{user_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable("restaurant_id") Long restaurantId, @PathVariable("user_id") Long userId) {
		restaurantService.disassociateRestaurantUser(restaurantId, userId);
	}

	@PutMapping("/{user_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable("restaurant_id") Long restaurantId, @PathVariable("user_id") Long userId) {
		restaurantService.associateRestaurantUser(restaurantId, userId);
	}
}