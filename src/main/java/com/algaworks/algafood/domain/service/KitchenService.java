package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.repository.KitchenRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {
	@Autowired
	private KitchenRepository kitchenRepository;

	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	public void remove(Long id) {
		try {
			kitchenRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("Kitchen (%d) Not found", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("Kitchen (%d) in use and cannot be removed", id));
		}
	}

	public List<Kitchen> listAll() {
		return kitchenRepository.findAll();
	}

	public Kitchen findById(Long id) {
		Kitchen kitchen = kitchenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Kitchen (%d) Not found", id)));

		return kitchen;
	}
	
	public Kitchen update(Kitchen kitchen, Long id) {
		Kitchen currentKitchen = kitchenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Kitchen (%d) Not found", id)));

		BeanUtils.copyProperties(kitchen, currentKitchen, "id");
		currentKitchen = kitchenRepository.save(currentKitchen);

		return currentKitchen;
	}
}