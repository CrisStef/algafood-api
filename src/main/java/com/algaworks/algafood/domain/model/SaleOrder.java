package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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

	@Column(nullable = false)
	private BigDecimal total_value;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime registrationDate;

	@Column(columnDefinition = "datetime")
	private LocalDateTime confirmationDate;

	@Column(columnDefinition = "datetime")
	private LocalDateTime cancellationDate;

	@Column(columnDefinition = "datetime")
	private LocalDateTime deliveryDate;

	@Embedded
	private Address deliveryAddress;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Payment payment;

	@Column(name = "status", nullable = false)
	private SaleOrderStatus saleOrderStatus;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "user_customer_id", nullable = false)
	private User customer;

	@OneToMany(mappedBy = "saleOrder")
	private List<SaleOrderItem> itens = new ArrayList<>();
}