package com.algaworks.algafood.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public CityNotFoundException(String message) {
		super(message);
	}

	public CityNotFoundException(Long id) {
		super(String.format("City not found! Id: %d", id));
	}
}