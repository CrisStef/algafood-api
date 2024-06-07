package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.algaworks.algafood.domain.model.enums.SaleOrderStatus;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SaleOrder {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private BigDecimal subtotal;

	@Column(name = "freight_rate", nullable = false)
	private BigDecimal freightRate;

	@Column(name = "total_value", nullable = false)
	private BigDecimal totalValue;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime registrationDate;

	@Column(columnDefinition = "datetime")
	private OffsetDateTime confirmationDate;

	@Column(columnDefinition = "datetime")
	private OffsetDateTime cancellationDate;

	@Column(columnDefinition = "datetime")
	private OffsetDateTime deliveryDate;

	@Embedded
	private Address deliveryAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Payment payment;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private SaleOrderStatus saleOrderStatus = SaleOrderStatus.CRIADO;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "user_customer_id", nullable = false)
	private User customer;

	@OneToMany(mappedBy = "saleOrder", cascade = CascadeType.ALL)
	private List<SaleOrderItem> itens = new ArrayList<>();

	public void calculateTotalValue() {
		getItens().forEach(SaleOrderItem::calculatePriceTotal);
		
		this.subtotal = getItens().stream()
			.map(item -> item.getTotalPrice())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		this.totalValue = this.subtotal.add(this.freightRate);
	}
}