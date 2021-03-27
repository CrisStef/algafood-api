package com.algaworks.algafood.domain.exception;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4879555631582992766L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}