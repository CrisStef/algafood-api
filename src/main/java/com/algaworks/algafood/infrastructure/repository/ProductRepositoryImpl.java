package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.ProductPhoto;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.ProductRepository;
import com.algaworks.algafood.domain.repository.ProductRepositoryQueries;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.repository.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.freeFreight;
import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.similarName;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public ProductPhoto save(ProductPhoto photo) {
		return manager.merge(photo);
	}
}