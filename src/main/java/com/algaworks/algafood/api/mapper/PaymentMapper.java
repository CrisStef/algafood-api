package com.algaworks.algafood.api.mapper;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.request.PaymentRequest;
import com.algaworks.algafood.api.model.response.PaymentResponse;
import com.algaworks.algafood.domain.model.Payment;

@Component
public class PaymentMapper {
	@Autowired
	private ModelMapper modelMapper;

	public Payment paymentRequestForPayment(PaymentRequest paymentRequest) {
		return modelMapper.map(paymentRequest, Payment.class);
	}

	public void copyPaymentForCurrentPayment(Payment payment, Payment currentPayment) {
		modelMapper.map(payment, currentPayment);
	}

	public PaymentResponse paymentForPaymentResponse(Payment payment) {
		return modelMapper.map(payment, PaymentResponse.class);
	}

	public List<PaymentResponse> paymentListForPaymentListResponse(Collection<Payment> payment) {
		return modelMapper.map(payment, new TypeToken<List<PaymentResponse>>(){}.getType());
	}
}