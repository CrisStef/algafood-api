package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.model.Customer;
import com.algaworks.algafood.di.model.annotations.NotifierType;
import com.algaworks.algafood.di.model.enums.LevelUrgency;
import com.algaworks.algafood.di.notification.Notifier;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomerActivationService {
	@NotifierType(LevelUrgency.NOT_URGENT)
	@Autowired
	private Notifier notifier;

		public void init() {
				System.out.println("Init");
		}

		public void destroy() {
				System.out.println("Destroy  BBBBBBBBBB");
		}

	public void activate(Customer customer) {
		customer.activate();
		notifier.notify(customer, "Seu cadastro no sistema está ativo!");
	}
}