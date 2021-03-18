package com.algaworks.algafood.di.notification;

import com.algaworks.algafood.di.model.Customer;
import com.algaworks.algafood.di.model.annotations.NotifierType;
import com.algaworks.algafood.di.model.enums.LevelUrgency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NotifierType(LevelUrgency.NOT_URGENT)
@Component
public class EmailNotifier implements Notifier {
	@Value("${notifier.email.host-server}")
	private String host;

	@Value("${notifier.email.host-server-port}")
	private Integer port;

	@Override
	public void notify(Customer customer, String message) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", customer.getName(), customer.getEmail(), message);
	}
}