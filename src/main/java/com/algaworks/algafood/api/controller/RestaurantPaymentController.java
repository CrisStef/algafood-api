package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.mapper.PaymentMapper;
import com.algaworks.algafood.api.model.response.PaymentResponse;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.service.RestaurantService;

@RestController
@RequestMapping("/restaurants/{restaurant_id}/payments")
public class RestaurantPaymentController {
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private PaymentMapper paymentMapper;

	@GetMapping
	public List<PaymentResponse> findAll(@PathVariable("restaurant_id") Long restaurantId) {
		Restaurant restaurant = restaurantService.findById(restaurantId);

		return paymentMapper.paymentListForPaymentListResponse(restaurant.getPayments());
	}
}