package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KitchenRegister {
	@PersistenceContext
	private EntityManager manager;

	public List<Kitchen> list() {
		return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
	}

	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return manager.merge(kitchen);
	}

	public Kitchen search(Long id) {
		return manager.find(Kitchen.class, id);
	}

	@Transactional
	public void remove(Kitchen kitchen) {
		kitchen = search(kitchen.getId());
		manager.remove(kitchen);
	}
}