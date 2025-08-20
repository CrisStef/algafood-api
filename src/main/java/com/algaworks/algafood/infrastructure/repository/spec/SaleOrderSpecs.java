package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.SaleOrder;
import com.algaworks.algafood.domain.repository.filter.SaleOrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class SaleOrderSpecs {
	public static Specification<SaleOrder> findAllSaleOrderByFilter(SaleOrderFilter saleOrderFilter) {
		return (root, query, builder) -> {
			root.fetch("restaurant").fetch("kitchen");
			root.fetch("customer");

			var predicates = new ArrayList<Predicate>();

			if (saleOrderFilter.getCustomerId() != null) {
				predicates.add(builder.equal(root.get("customer"), saleOrderFilter.getCustomerId()));
			}

			if (saleOrderFilter.getRestaurantId() != null) {
				predicates.add(builder.equal(root.get("restaurant"), saleOrderFilter.getRestaurantId()));
			}

			if (saleOrderFilter.getStartRegistrationDate() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("registrationDate"),
						saleOrderFilter.getStartRegistrationDate()));
			}

			if (saleOrderFilter.getEndRegistrationDate() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("registrationDate"),
						saleOrderFilter.getEndRegistrationDate()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}