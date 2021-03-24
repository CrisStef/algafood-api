package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.repository.CityRepository;

import org.springframework.stereotype.Component;

@Component
public class CityRepositoryImpl implements CityRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<City> listAll() {
		return manager.createQuery("from City", City.class).getResultList();
	}

	@Override
	public City findById(Long id) {
		return manager.find(City.class, id);
	}

	@Transactional
	@Override
	public City save(City city) {
		return manager.merge(city);
	}

	@Transactional
	@Override
	public void remove(City city) {
		city = findById(city.getId());
		manager.remove(city);
	}
}