package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.mapper.KitchenMapper;
import com.algaworks.algafood.api.model.request.KitchenRequest;
import com.algaworks.algafood.api.model.response.KitchenResponse;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.KitchenNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KitchenService {
	@Autowired
	private KitchenRepository kitchenRepository;

	private static final String MSG_KITCHEN_IN_USE = "Kitchen (%d) in use and cannot be removed";

	@Autowired 
	private KitchenMapper kitchenMapper;

	public KitchenResponse create(KitchenRequest kitchenRequest) {
		Kitchen kitchen = kitchenRepository.save(kitchenMapper.kitchenRequestForKitchen(kitchenRequest));

		return kitchenMapper.kitchenForKitchenResponse(kitchen);
	}

	public KitchenResponse getById(Long id) {
		return kitchenMapper.kitchenForKitchenResponse(this.findById(id));
	}

	public List<KitchenResponse> getByName(String name) {
		return kitchenMapper.kitchenListForKitchenListResponse(this.findByName(name));
	}

	public List<KitchenResponse> findAll() {
		return kitchenMapper.kitchenListForKitchenListResponse(this.listAll());
	}

	public KitchenResponse alter(@Valid KitchenRequest kitchenRequest, Long id) {
		Kitchen kitchen = this.update(kitchenMapper.kitchenRequestForKitchen(kitchenRequest), id);

		return kitchenMapper.kitchenForKitchenResponse(kitchen);
	}

	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	@Transactional
	public void remove(Long id) {
		try {
			kitchenRepository.deleteById(id);
			kitchenRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new KitchenNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_KITCHEN_IN_USE, id));
		}
	}

	private List<Kitchen> listAll() {
		return kitchenRepository.findAll();
	}

	public Kitchen findById(Long id) {
		Kitchen kitchen = kitchenRepository.findById(id).orElseThrow(() -> new KitchenNotFoundException(id));

		return kitchen;
	}
	
	@Transactional
	private Kitchen update(Kitchen kitchen, Long id) {
		Kitchen currentKitchen = findById(id);

		kitchenMapper.copyKitchenForCurrentKitchen(kitchen, currentKitchen);

		currentKitchen = kitchenRepository.save(currentKitchen);

		return currentKitchen;
	}

	public List<Kitchen> findByName(String name) {
		return kitchenRepository.findAllByNameContaining(name);
	}
}