package com.algaworks.algafood.domain.exception;

public class SaleOrderNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public SaleOrderNotFoundException(String code) {
		super(String.format("Sale order not found! Code: %s", code));
	}
}