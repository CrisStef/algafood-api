package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public abstract class EntityNotFoundException extends BusinessException {
	private static final long serialVersionUID = 4879555631582992766L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}