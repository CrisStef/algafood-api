package com.algaworks.algafood.di.notification;

import com.algaworks.algafood.di.model.Customer;
import com.algaworks.algafood.di.model.annotations.NotifierType;
import com.algaworks.algafood.di.model.enums.LevelUrgency;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@NotifierType(LevelUrgency.NOT_URGENT)
@Component
public class EmailNotifierMock implements Notifier {
	public EmailNotifierMock() {
		System.out.println("NotificadorEmail MOCK");
	}
	
	@Override
	public void notify(Customer customer, String message) {
		System.out.printf("MOCK: Notificação seria enviada para %s através do e-mail %s: %s\n", customer.getName(), customer.getEmail(), message);
	}
}