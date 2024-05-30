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

	@DeleteMapping("/{payment_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable("restaurant_id") Long restaurantId, @PathVariable("payment_id") Long paymentId) {
		restaurantService.disassociateRestaurantPayment(restaurantId, paymentId);
	}

	@PutMapping("/{payment_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable("restaurant_id") Long restaurantId, @PathVariable("payment_id") Long paymentId) {
		restaurantService.associateRestaurantPayment(restaurantId, paymentId);
	}
}