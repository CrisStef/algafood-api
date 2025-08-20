package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("FROM Product p WHERE p.active = true and p.restaurant = :restaurant")
    List<Product> findActiveByRestaurant(Restaurant restaurant);
}