package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SaleOrderItem {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "unit_price", nullable = false)
	private BigDecimal unitPrice;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private BigDecimal totalPrice;

	private String observation;

	@ManyToOne
	@JoinColumn(nullable = false)
	private SaleOrder saleOrder;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product;

	public void calculatePriceTotal() {
		BigDecimal unitPrice = this.getUnitPrice();
		Integer quantity = this.getQuantity();
	
		if (unitPrice == null) {
			unitPrice = BigDecimal.ZERO;
		}
	
		if (quantity == null) {
			quantity = 0;
		}
	
		this.setTotalPrice(unitPrice.multiply(new BigDecimal(quantity)));
	}
}