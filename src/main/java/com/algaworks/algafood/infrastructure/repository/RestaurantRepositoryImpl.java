package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.freeFreight;
import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.similarName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
	@PersistenceContext
	private EntityManager manager;

	@Autowired @Lazy
	private RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> find(String name, BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		List<Predicate> predicates = new ArrayList<>();

		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);

		if (StringUtils.hasText(name)) {
			predicates.add(builder.like(root.get("name"), "%" + name + "%"));
		}

		if (initialFraightRate != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("freightRate"), initialFraightRate));
		}

		if (finalFraightRate != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("freightRate"), finalFraightRate));
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Restaurant> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<Restaurant> findRestaurantFilter(String name) {
		return restaurantRepository.findAll(freeFreight().and(similarName(name)));
	}
}