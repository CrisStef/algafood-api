package com.algaworks.algafood.domain.exception;

public class ProductPhotoNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public ProductPhotoNotFoundException(String message) {
		super(message);
	}

	public ProductPhotoNotFoundException(Long productId, Long restaurantId) {
		super(String.format("Product photo not found for product code: %d and restaurant cod: %d", productId, restaurantId));
	}
}