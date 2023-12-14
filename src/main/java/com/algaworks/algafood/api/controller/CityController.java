package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.CityRequest;
import com.algaworks.algafood.api.model.response.CityResponse;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.StateNotFoundException;
import com.algaworks.algafood.domain.service.CityService;

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
@RequestMapping("/cities")
public class CityController {
	@Autowired
	private CityService cityService;

	@GetMapping
	public List<CityResponse> findAll() {
		return cityService.findAll();
	}

	@GetMapping("/{city_id}")
	public CityResponse getById(@PathVariable("city_id") Long id) {
		return cityService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityResponse create(@RequestBody @Valid CityRequest city) {
		try {
			return cityService.create(city);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{city_id}")
	public CityResponse alter(@RequestBody @Valid CityRequest city, @PathVariable("city_id") Long id) {
		try {
			return cityService.alter(city, id);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{city_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("city_id") Long id) {
		cityService.remove(id);
	}
}