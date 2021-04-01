package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algaworks.algafood.domain.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryQueries {
	List<Restaurant> findByFreightRateBetween(BigDecimal initialFraightRate, BigDecimal finalFraightRate);

	List<Restaurant> findByNameAndKitchenId(String name, @Param("id") Long kitchenId);
}