package com.algaworks.algafood.di.notification;

import com.algaworks.algafood.di.model.Customer;
import com.algaworks.algafood.di.model.annotations.NotifierType;
import com.algaworks.algafood.di.model.enums.LevelUrgency;

import org.springframework.stereotype.Component;

@NotifierType(LevelUrgency.URGENT)
@Component
public class SmsNotifier implements Notifier {
	@Override
	public void notify(Customer customer, String message) {
		System.out.printf("Notificando %s por SMS através do telefone %s: %s\n", customer.getName(), customer.getTelephone(), message);
	}
}