package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.KitchenRequest;
import com.algaworks.algafood.api.model.response.KitchenResponse;
import com.algaworks.algafood.domain.service.KitchenService;

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
@RequestMapping(value = "/kitchens")
public class KitchenController {
	@Autowired
	private KitchenService kitchenService;

	@GetMapping
	public List<KitchenResponse> findAll() {
		return kitchenService.findAll();
	}

	@GetMapping("/{kitchen_id}")
	public KitchenResponse getById(@PathVariable("kitchen_id") Long id) {
		return kitchenService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public KitchenResponse create(@RequestBody @Valid KitchenRequest kitchen) {
		return kitchenService.create(kitchen);
	}

	@PutMapping("/{kitchen_id}")
	public KitchenResponse alter(@PathVariable("kitchen_id") Long id, @RequestBody @Valid KitchenRequest kitchen) {
		return kitchenService.alter(kitchen, id);
	}

	@DeleteMapping("/{kitchen_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("kitchen_id") Long id) {
		kitchenService.remove(id);
	}

	@GetMapping("/by-name")
	public List<KitchenResponse> getByName(String name) {
		return kitchenService.getByName(name);
	}
}