package com.algaworks.algafood.domain.exception;

public class PaymentNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public PaymentNotFoundException(String message) {
		super(message);
	}

	public PaymentNotFoundException(Long id) {
		super(String.format("Payment not found! Id: %d", id));
	}
}