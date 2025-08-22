package com.algaworks.algafood.infrastructure.service;

import com.algaworks.algafood.domain.filter.DailySaleFilter;
import com.algaworks.algafood.domain.model.SaleOrder;
import com.algaworks.algafood.domain.model.dto.DailySale;
import com.algaworks.algafood.domain.model.enums.SaleOrderStatus;
import com.algaworks.algafood.domain.service.ISaleQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SaleQueryServiceImpl implements ISaleQueryService {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<DailySale> findDailySale(DailySaleFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(DailySale.class);
        var root = query.from(SaleOrder.class);
        List<Predicate> predicates = new ArrayList<>();

        var functionDateRegistrationDate = builder.function(
                "date", Date.class, root.get("registrationDate"));

        var selection = builder.construct(DailySale.class,
                functionDateRegistrationDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue")));

        query.select(selection);

        if (filter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }

        if (filter.getStartRegistrationDate() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("registrationDate"),
                    filter.getStartRegistrationDate()));
        }

        if (filter.getEndRegistrationDate() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("registrationDate"),
                    filter.getEndRegistrationDate()));
        }

        predicates.add(root.get("saleOrderStatus").in(
                SaleOrderStatus.CONFIRMED, SaleOrderStatus.DELIVERED));

        query.where(predicates.toArray(new Predicate[0]));

        query.groupBy(functionDateRegistrationDate);

        return manager.createQuery(query).getResultList();
    }
}