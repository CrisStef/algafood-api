package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.api.model.KitchenXmlWrapper;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;
import com.algaworks.algafood.domain.service.KitchenService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	@Autowired
	private KitchenRepository kitchenRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Kitchen> list() {
		return kitchenRepository.listAll();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public KitchenXmlWrapper listXml() {
		return new KitchenXmlWrapper(kitchenRepository.listAll());
	}

	@GetMapping("/{kitchen_id}")
	public ResponseEntity<Kitchen> find(@PathVariable("kitchen_id") Long id) {
		Kitchen kitchen = kitchenRepository.findById(id);
		return ResponseEntity.ok().body(kitchen);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen save(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}

	@PutMapping("/{kitchen_id}")
	public ResponseEntity<Kitchen> update(@PathVariable("kitchen_id") Long id, @RequestBody Kitchen kitchen) {
		Kitchen currentKitchen = kitchenRepository.findById(id);
		BeanUtils.copyProperties(kitchen, currentKitchen, "id");
		kitchenRepository.save(currentKitchen);
		return ResponseEntity.ok().body(currentKitchen);
	}

	@DeleteMapping("/{kitchen_id}")
	public ResponseEntity<Kitchen> remove(@PathVariable("kitchen_id") Long id) {
		try {
			kitchenService.remove(id);
			return ResponseEntity.noContent().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}