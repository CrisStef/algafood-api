package com.algaworks.algafood.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Long id) {
		super(String.format("User not found! Id: %d", id));
	}
}