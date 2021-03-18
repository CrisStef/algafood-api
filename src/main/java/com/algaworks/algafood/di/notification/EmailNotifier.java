package com.algaworks.algafood.di.notification;

import com.algaworks.algafood.config.NotifierProperties;
import com.algaworks.algafood.di.model.Customer;
import com.algaworks.algafood.di.model.annotations.NotifierType;
import com.algaworks.algafood.di.model.enums.LevelUrgency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NotifierType(LevelUrgency.NOT_URGENT)
@Component
public class EmailNotifier implements Notifier {
	@Autowired
    private NotifierProperties properties;

	@Override
	public void notify(Customer customer, String message) {
        System.out.println(properties.getHostServer());
		System.out.printf("Notificando %s através do e-mail %s: %s\n", customer.getName(), customer.getEmail(), message);
	}
}