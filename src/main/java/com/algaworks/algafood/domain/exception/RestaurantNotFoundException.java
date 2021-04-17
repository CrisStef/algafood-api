package com.algaworks.algafood.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public RestaurantNotFoundException(String message) {
		super(message);
	}

	public RestaurantNotFoundException(Long id) {
		super(String.format("Restaurant not found! Id: %d", id));
	}
}