package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.StateRequest;
import com.algaworks.algafood.api.model.response.StateResponse;
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
	public List<StateResponse> findAll() {
		return stateService.findAll();
	}

	@GetMapping("/{state_id}")
	public StateResponse getById(@PathVariable("state_id") Long id) {
		return stateService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateResponse create(@RequestBody @Valid StateRequest state) {
		return stateService.create(state);
	}

	@PutMapping("/{state_id}")
	public StateResponse alter(@RequestBody @Valid StateRequest state, @PathVariable("state_id") Long id) {
		return stateService.alter(state, id);
	}

	@DeleteMapping("/{state_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("state_id") Long id) {
		stateService.remove(id);
	}
}