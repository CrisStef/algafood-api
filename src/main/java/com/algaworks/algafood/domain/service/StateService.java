package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.mapper.StateMapper;
import com.algaworks.algafood.api.model.request.StateRequest;
import com.algaworks.algafood.api.model.response.StateResponse;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.StateNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {
	@Autowired
	private StateRepository stateRepository;

	private static final String MSG_STATE_IN_USE = "State (%d) in use and cannot be removed";

	@Autowired
	private StateMapper stateMapper;

	public List<StateResponse> findAll() {
		return stateMapper.stateListForStateListResponse(this.listAll());
	}
	
	public StateResponse getById(Long id) {
		return stateMapper.stateForStateResponse(this.findById(id));
	}
	
	public StateResponse create(@Valid StateRequest stateRequest) {
		State state = stateMapper.stateRequestForState(stateRequest);

		return stateMapper.stateForStateResponse(this.save(state));
	}
	
	public StateResponse alter(@Valid StateRequest stateRequest, Long id) {
		State state = stateMapper.stateRequestForState(stateRequest);

		return stateMapper.stateForStateResponse(this.update(state, id));
	}

	private List<State> listAll() {
		return stateRepository.findAll();
	}

	public State findById(Long id) {
		State state = stateRepository.findById(id)
							.orElseThrow(() -> new StateNotFoundException(id));

		return state;
	}

	@Transactional
	private State save(State state) {
		return stateRepository.save(state);
	}

	@Transactional
	private State update(State state, Long id) {
		State currentState = findById(id);

		BeanUtils.copyProperties(state, currentState, "id");
		currentState = stateRepository.save(currentState);

		return currentState;
	}

	@Transactional
	public void remove(Long id) {
		try {
			stateRepository.deleteById(id);
			stateRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new StateNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_STATE_IN_USE, id));
		}
	}
}