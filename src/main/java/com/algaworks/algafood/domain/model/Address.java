package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Address {
	@Column(name = "address_zip_code")
	private String zipCode;

	@Column(name = "address_public_place")
	private String publicPlace;

	@Column(name = "address_number")
	private String number;

	@Column(name = "address_complement")
	private String complement;

	@Column(name = "address_district")
	private String district;

	@ManyToOne
	@JoinColumn(name = "address_city_id")
	private City city;
}