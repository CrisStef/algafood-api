package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;
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
	public List<Kitchen> listAll() {
		return kitchenService.listAll();
	}

	@GetMapping("/{kitchen_id}")
	public Kitchen findById(@PathVariable("kitchen_id") Long id) {
		Kitchen kitchen = kitchenService.findById(id);

		return kitchen;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen create(@RequestBody Kitchen kitchen) {
		kitchen = kitchenService.save(kitchen);

		return kitchen;
	}

	@PutMapping("/{kitchen_id}")
	public Kitchen update(@PathVariable("kitchen_id") Long id, @RequestBody Kitchen kitchen) {
		kitchen = kitchenService.update(kitchen, id);

		return kitchen;
	}

	@DeleteMapping("/{kitchen_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("kitchen_id") Long id) {
		kitchenService.remove(id);
	}

	@GetMapping("/by-name")
	public List<Kitchen> findByName(String name) {
		return kitchenService.findByName(name);
	}
}