package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CityRepository extends JpaRepository<City, Long> {}