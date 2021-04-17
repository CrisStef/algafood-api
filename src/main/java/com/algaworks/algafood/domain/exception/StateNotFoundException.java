package com.algaworks.algafood.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public StateNotFoundException(String message) {
		super(message);
	}

	public StateNotFoundException(Long id) {
		super(String.format("State not found! Id: %d", id));
	}
}