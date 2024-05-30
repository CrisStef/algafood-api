package com.algaworks.algafood.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(Long id) {
		super(String.format("Product not found! Id: %d", id));
	}
}