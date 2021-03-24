package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.State;

public interface StateRepository {
	List<State> listAll();

	State findById(Long id);

	State save(State state);

	void remove(State state);
}