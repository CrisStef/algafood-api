package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.ProductPhoto;
import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {
    @Query("FROM Product p WHERE p.active = true and p.restaurant = :restaurant")
    List<Product> findActiveByRestaurant(Restaurant restaurant);

    @Query("SELECT f FROM ProductPhoto f JOIN f.product p " +
            "WHERE p.restaurant.id = :restaurantId AND f.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);
}