package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.stereotype.Component;

@Component
public class StateRepositoryImpl implements StateRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<State> listAll() {
		return manager.createQuery("from State", State.class).getResultList();
	}

	@Override
	public State findById(Long id) {
		return manager.find(State.class, id);
	}

	@Transactional
	@Override
	public State save(State state) {
		return manager.merge(state);
	}

	@Transactional
	@Override
	public void remove(State state) {
		state = findById(state.getId());
		manager.remove(state);
	}
}