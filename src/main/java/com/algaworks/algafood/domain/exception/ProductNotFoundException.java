package com.algaworks.algafood.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(Long id) {
		super(String.format("Product not found! Id: %d", id));
	}

	public ProductNotFoundException(Long productId, Long restaurantId) {
		super(String.format("There is no product registration with code %s for the restaurant with code %s", productId, restaurantId));
	}
}