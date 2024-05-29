package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Kitchen;

import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {
	List<Kitchen> findAllByNameContaining(String name);
}