package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.service.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<?> findById(@PathVariable("state_id") Long id) {
		try {
			State state = stateService.findById(id);

			return ResponseEntity.ok().body(state);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody State state) {
		state = stateService.create(state);
		return ResponseEntity.status(HttpStatus.CREATED).body(state);
	}

	@PutMapping("/{state_id}")
	public ResponseEntity<?> update(@RequestBody State state, @PathVariable("state_id") Long id) {
		try {
			state = stateService.update(state, id);
			return ResponseEntity.ok().body(state);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{state_id}")
	public ResponseEntity<?> remove(@PathVariable("state_id") Long id) {
		try {
			stateService.remove(id);
			return ResponseEntity.noContent().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}