package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
	List<Kitchen> findAllByNameContaining(String name);
}