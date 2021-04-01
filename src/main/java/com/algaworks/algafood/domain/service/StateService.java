package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateService {
	@Autowired
	private StateRepository stateRepository;

	public List<State> listAll() {
		return stateRepository.findAll();
	}

	public State findById(Long id) {
		State state = stateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("State not found! Id: %d", id)));

		return state;
	}

	public State create(State state) {
		return stateRepository.save(state);
	}

	public State update(State state, Long id) {
		State currentState = stateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("State not found! Id: %d", id)));

		BeanUtils.copyProperties(state, currentState, "id");
		currentState = stateRepository.save(currentState);

		return currentState;
	}

	public void remove(Long id) {
		try {
			stateRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("State (%d) Not found", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("State (%d) in use and cannot be removed", id));
		}
	}
}