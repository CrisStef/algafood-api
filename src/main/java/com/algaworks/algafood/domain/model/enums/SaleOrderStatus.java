package com.algaworks.algafood.domain.model.enums;

import java.util.List;

public enum SaleOrderStatus {
	CREATED("Criado"),
	CONFIRMED("Confirmado", CREATED),
	DELIVERED("Entregue", CONFIRMED),
	CANCELED("Cancelado", CREATED);

	private String description;
	private List<SaleOrderStatus> saleOrderStatusAllowed;

	SaleOrderStatus(String description, SaleOrderStatus... saleOrderStatusAllowed) {
		this.description = description;
		this.saleOrderStatusAllowed = List.of(saleOrderStatusAllowed);
	}

	public String getDescription() {
		return this.description;
	}

	public boolean validateStatusChange(SaleOrderStatus newStatus) {
		return !newStatus.saleOrderStatusAllowed.contains(this);
	}
}