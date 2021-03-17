package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.model.Customer;

public class CustomerActivatedEvent {

	private Customer customer;

	public CustomerActivatedEvent(Customer customer) {
		super();
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
}