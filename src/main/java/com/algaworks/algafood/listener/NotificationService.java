package com.algaworks.algafood.listener;

import com.algaworks.algafood.di.model.annotations.NotifierType;
import com.algaworks.algafood.di.model.enums.LevelUrgency;
import com.algaworks.algafood.di.notification.Notifier;
import com.algaworks.algafood.di.service.CustomerActivatedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
  @NotifierType(LevelUrgency.NOT_URGENT)
  @Autowired
  private Notifier notifier;

	@EventListener
	public void customerActivatedListener(CustomerActivatedEvent event) {
    notifier.notify(event.getCustomer(), "Seu cadastro no sistema está ativo!");
	}
}