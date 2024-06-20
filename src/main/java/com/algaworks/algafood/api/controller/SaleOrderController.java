package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.algaworks.algafood.api.model.request.SaleOrderRequest;
import com.algaworks.algafood.api.model.response.SaleOrderListResponse;
import com.algaworks.algafood.api.model.response.SaleOrderResponse;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.service.SaleOrderService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/saleOrders")
public class SaleOrderController {
	@Autowired
	private SaleOrderService saleOrderService;

	// @GetMapping
	// public List<SaleOrderListResponse> findAll() {
	// 	return saleOrderService.findAll();
	// }

	@GetMapping
	public MappingJacksonValue findAll(@RequestParam(required = false) String fields) {
		List<SaleOrderListResponse> saleOrderListResponse = saleOrderService.findAll();
		MappingJacksonValue saleOrderWrapper = new MappingJacksonValue(saleOrderListResponse);

		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("saleOrderFilter", SimpleBeanPropertyFilter.serializeAll());

		if (StringUtils.isNotBlank(fields)) {
			filterProvider.addFilter("saleOrderFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields.split(",")));
		}

		saleOrderWrapper.setFilters(filterProvider);

		return saleOrderWrapper;
	}

	@GetMapping("/{sale_order_code}")
	public SaleOrderResponse getById(@PathVariable("sale_order_code") String saleOrderCode) {
		return saleOrderService.getByCode(saleOrderCode);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SaleOrderResponse create(@Valid @RequestBody SaleOrderRequest saleOrderRequest) {
		try {
			return saleOrderService.create(saleOrderRequest);
		} catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
}