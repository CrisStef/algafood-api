package com.algaworks.algafood.domain.service;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.model.SaleOrder;
import com.algaworks.algafood.domain.model.enums.SaleOrderStatus;

@Service
public class SaleOrderManagementService {
	@Autowired
	private SaleOrderService saleOrderService;

	private final Map<SaleOrderStatus, Set<SaleOrderStatus>> validTransitions;

	public SaleOrderManagementService() {
		validTransitions = new EnumMap<>(SaleOrderStatus.class);
		validTransitions.put(SaleOrderStatus.CREATED, EnumSet.of(SaleOrderStatus.CONFIRMED, SaleOrderStatus.CANCELED));
		validTransitions.put(SaleOrderStatus.CONFIRMED, EnumSet.of(SaleOrderStatus.DELIVERED));
	}

	@Transactional
	public void confirmationSaleOrder(Long saleOrderId) {
		SaleOrder saleOrder = saleOrderService.findById(saleOrderId);

		this.validateStatusChange(saleOrder, SaleOrderStatus.CONFIRMED);

		saleOrder.confirmation();
	}

	@Transactional
	public void deliverySaleOrder(Long saleOrderId) {
		SaleOrder saleOrder = saleOrderService.findById(saleOrderId);

		this.validateStatusChange(saleOrder, SaleOrderStatus.DELIVERED);

		saleOrder.delivered();
	}

	@Transactional
	public void cancellationSaleOrder(Long saleOrderId) {
		SaleOrder saleOrder = saleOrderService.findById(saleOrderId);

		this.validateStatusChange(saleOrder, SaleOrderStatus.CANCELED);

		saleOrder.canceled();
	}

	private void validateStatusChange(SaleOrder saleOrder, SaleOrderStatus newStatus) {
		SaleOrderStatus currentStatus = saleOrder.getSaleOrderStatus();
		Set<SaleOrderStatus> allowedTransitions = validTransitions.get(currentStatus);
	
		if (allowedTransitions == null || !allowedTransitions.contains(newStatus)) {
			throw new BusinessException(
				String.format("Sale order status %s cannot be changed from %s to %s",
							  saleOrder.getId(), currentStatus.getDescription(), newStatus.getDescription()));
		}
	}
}