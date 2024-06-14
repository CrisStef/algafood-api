package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.mapper.SaleOrderMapper;
import com.algaworks.algafood.api.model.request.SaleOrderRequest;
import com.algaworks.algafood.api.model.response.SaleOrderListResponse;
import com.algaworks.algafood.api.model.response.SaleOrderResponse;
import com.algaworks.algafood.domain.exception.PaymentNotFoundException;
import com.algaworks.algafood.domain.exception.ProductNotFoundException;
import com.algaworks.algafood.domain.exception.SaleOrderNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.Payment;
import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.model.SaleOrder;
import com.algaworks.algafood.domain.repository.SaleOrderRepository;

@Service
public class SaleOrderService {
	@Autowired
	private SaleOrderRepository saleOrderRepository;

	@Autowired
	private SaleOrderMapper saleOrderMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CityService cityService;

	public List<SaleOrderListResponse> findAll() {
		return saleOrderMapper.saleOrderListForSaleOrderListResponse(this.listAll());
	}

	public SaleOrderResponse getByCode(String code) {
		return saleOrderMapper.saleOrderForSaleOrderResponse(this.findByCode(code));
	}

	@Transactional
	public SaleOrderResponse create(SaleOrderRequest saleOrderRequest) {
		SaleOrder saleOrder = saleOrderMapper.saleOrderRequestForSaleOrder(saleOrderRequest);
		Restaurant restaurant = restaurantService.findById(saleOrderRequest.getRestaurant().getId());
		City city = cityService.findById(saleOrder.getDeliveryAddress().getCity().getId());

		saleOrder.setCustomer(userService.findById(1L));//TODO: melhoria com autenticacao
		saleOrder.setRestaurant(restaurant);
		saleOrder.setFreightRate(restaurant.getFreightRate());
		this.validRestaurantPayment(saleOrder);
		saleOrder.getDeliveryAddress().setCity(city);

		this.validSaleOrderItens(saleOrder, restaurant);

		saleOrder.calculateTotalValue();

		this.save(saleOrder);

		return saleOrderMapper.saleOrderForSaleOrderResponse(saleOrder);
	}

	private void validSaleOrderItens(SaleOrder saleOrder, Restaurant restaurant) {
		saleOrder.getItens().stream().forEach(item -> {
			Product product = productService.findById(item.getProduct().getId());

			if (!restaurant.getProducts().contains(product)) {
				throw new ProductNotFoundException(product.getId(), restaurant.getId());
			}

			item.setProduct(product);
			item.setSaleOrder(saleOrder);
			item.setUnitPrice(product.getPrice());
		});
	}

	private void validRestaurantPayment(SaleOrder saleOrder) {
		Payment payment = paymentService.findById(saleOrder.getPayment().getId());

		if (!saleOrder.getRestaurant().getPayments().contains(payment)) {
			throw new PaymentNotFoundException(String.format("This payment '%s' not accepted by this restaurant.", payment.getDescription()));
		}

		saleOrder.setPayment(payment);
	}

	@Transactional
	private SaleOrder save(SaleOrder saleOrder) {
		return saleOrderRepository.save(saleOrder);
	}
	
	private List<SaleOrder> listAll() {
		return saleOrderRepository.findAll();
	}

	public SaleOrder findByCode(String code) {
		SaleOrder saleOrder = saleOrderRepository.findByCode(code)
							.orElseThrow(() -> new SaleOrderNotFoundException(code));

		return saleOrder;
	}
}