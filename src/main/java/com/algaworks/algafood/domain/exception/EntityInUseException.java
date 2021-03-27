package com.algaworks.algafood.domain.exception;

public class EntityInUseException extends RuntimeException {
	private static final long serialVersionUID = -8113729813378571040L;

	public EntityInUseException(String message) {
		super(message);
	}
}