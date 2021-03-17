package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
@Component
public class CustomerActivationService {
  @Autowired
  private ApplicationEventPublisher eventPublisher;

	public void activate(Customer customer) {
    customer.activate();
		eventPublisher.publishEvent(new CustomerActivatedEvent(customer));
	}
}