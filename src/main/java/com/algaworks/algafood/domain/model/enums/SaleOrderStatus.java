package com.algaworks.algafood.domain.model.enums;

public enum SaleOrderStatus {
	CREATED("Criado"),
	CONFIRMED("Confirmado"),
	DELIVERED("Entregue"),
	CANCELED("Cancelado");

	private String description;

	SaleOrderStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}