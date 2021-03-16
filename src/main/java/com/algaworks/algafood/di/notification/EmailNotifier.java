package com.algaworks.algafood.di.notification;

import com.algaworks.algafood.di.model.Customer;
import com.algaworks.algafood.di.model.annotations.NotifierType;
import com.algaworks.algafood.di.model.enums.LevelUrgency;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@NotifierType(LevelUrgency.NOT_URGENT)
@Component
public class EmailNotifier implements Notifier {
	public EmailNotifier() {
		System.out.println("NotificadorEmail REAL");
	}
	
	@Override
	public void notify(Customer customer, String message) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", customer.getName(), customer.getEmail(), message);
	}
}