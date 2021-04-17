package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/states")
public class StateController {
	@Autowired
	private StateService stateService;

	@GetMapping
	public List<State> listAll() {
		return stateService.listAll();
	}

	@GetMapping("/{state_id}")
	public State findById(@PathVariable("state_id") Long id) {
		State state = stateService.findById(id);

		return state;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State create(@RequestBody State state) {
		state = stateService.create(state);

		return state;
	}

	@PutMapping("/{state_id}")
	public State update(@RequestBody State state, @PathVariable("state_id") Long id) {
		state = stateService.update(state, id);

		return state;
	}

	@DeleteMapping("/{state_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("state_id") Long id) {
		stateService.remove(id);
	}
}