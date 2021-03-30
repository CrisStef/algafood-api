package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class StateService {
	@Autowired
	private StateRepository stateRepository;

	public List<State> list() {
		return stateRepository.listAll();
	}

	public State findById(Long id) {
		State state = stateRepository.findById(id);

		if (state == null) {
			throw new EntityNotFoundException(String.format("State not found! Id: %d", id));
		}

		return state;
	}

	public State create(State state) {
		return stateRepository.save(state);
	}

	public State update(State state, Long id) {
		State currentState = stateRepository.findById(id);

		if (currentState == null) {
			throw new EntityNotFoundException(String.format("State not found! Id: %d", id));
		}

		BeanUtils.copyProperties(state, currentState, "id");
		currentState = stateRepository.save(currentState);

		return currentState;
	}

	public void remove(Long id) {
		State state = stateRepository.findById(id);

		if (state == null) {
			throw new EntityNotFoundException(String.format("State not found! Id: %d", id));
		}

		try {
			stateRepository.remove(state);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("State (%d) in use and cannot be removed", id));
		}
	}
}