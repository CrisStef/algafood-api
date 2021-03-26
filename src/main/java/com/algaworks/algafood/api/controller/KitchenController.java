package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.api.model.KitchenXmlWrapper;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {
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

		//return ResponseEntity.status(HttpStatus.OK).body(kitchen);
		//return ResponseEntity.ok().body(kitchen);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, "http://api.algafood.local:8080/kitchens");
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.headers(headers)
				.build();
	}
}