package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.SaleOrder;
@Repository
public interface SaleOrderRepository extends CustomJpaRepository<SaleOrder, Long> {
	@Query("FROM SaleOrder so JOIN FETCH so.customer JOIN FETCH so.restaurant r JOIN FETCH r.kitchen")
	List<SaleOrder> findAll();

	Optional<SaleOrder> findByCode(String code);
}