package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.algaworks.algafood.core.validation.ValueZeroDescription;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValueZeroDescription(fieldValue = "freightRate", fieldDescription = "name", mandatoryDescription = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "freight_rate", nullable = false)
	private BigDecimal freightRate;

	@ManyToOne
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;

	@Column(nullable = false)
	private Boolean status = Boolean.TRUE;

	@Embedded
	private Address address;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime registrationDate;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime updateDate;

	@ManyToMany
	@JoinTable(name = "payment_restaurant",
				joinColumns = @JoinColumn(name = "restaurant_id"), 
				inverseJoinColumns = @JoinColumn(name = "payment_id"))
	private List<Payment> payments = new ArrayList<>();

	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();

	public void activate() {
		this.setStatus(Boolean.TRUE);
	}

	public void disable() {
		this.setStatus(Boolean.FALSE);
	}
}