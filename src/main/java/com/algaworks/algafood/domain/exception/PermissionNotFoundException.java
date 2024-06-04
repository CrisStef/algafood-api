package com.algaworks.algafood.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public PermissionNotFoundException(String message) {
		super(message);
	}

	public PermissionNotFoundException(Long id) {
		super(String.format("Permission not found! Id: %d", id));
	}
}