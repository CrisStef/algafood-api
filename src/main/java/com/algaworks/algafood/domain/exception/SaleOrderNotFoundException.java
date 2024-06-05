package com.algaworks.algafood.domain.exception;

public class SaleOrderNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public SaleOrderNotFoundException(String message) {
		super(message);
	}

	public SaleOrderNotFoundException(Long id) {
		super(String.format("Sale order not found! Id: %d", id));
	}
}