package com.algaworks.algafood.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public KitchenNotFoundException(String message) {
		super(message);
	}

	public KitchenNotFoundException(Long id) {
		super(String.format("Kitchen not found! Id: %d", id));
	}
}