package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Kitchen> listAll() {
		return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
	}

	@Override
	public Kitchen findById(Long id) {
		return manager.find(Kitchen.class, id);
	}

	@Transactional
	@Override
	public Kitchen save(Kitchen kitchen) {
		return manager.merge(kitchen);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		Kitchen kitchen = findById(id);

		if (kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(kitchen);
	}
}