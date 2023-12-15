package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.mapper.PaymentMapper;
import com.algaworks.algafood.api.model.request.PaymentRequest;
import com.algaworks.algafood.api.model.response.PaymentResponse;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.PaymentNotFoundException;
import com.algaworks.algafood.domain.model.Payment;
import com.algaworks.algafood.domain.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentMapper paymentMapper;

	private static final String MSG_PAYMENT_IN_USE = "Payment (%d) in use and cannot be removed";

	public PaymentResponse create(PaymentRequest paymentRequest) {
		Payment payment = paymentMapper.paymentRequestForPayment(paymentRequest);

		return paymentMapper.paymentForPaymentResponse(this.save(payment));
	}

	public List<PaymentResponse> findAll() {
		return paymentMapper.paymentListForPaymentListResponse(this.listAll());
	}

	public PaymentResponse getById(Long id) {
		return paymentMapper.paymentForPaymentResponse(this.findById(id));
	}
	
	public PaymentResponse alter(@Valid PaymentRequest paymentRequest, Long id) {
		Payment payment = paymentMapper.paymentRequestForPayment(paymentRequest);

		return paymentMapper.paymentForPaymentResponse(this.update(payment, id));
	}

	public List<Payment> listAll() {
		return paymentRepository.findAll();
	}

	public Payment findById(Long id) {
		Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));

		return payment;
	}

	@Transactional
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Transactional
	public Payment update(Payment payment, Long id) {
		Payment currentPayment = findById(id);

		paymentMapper.copyPaymentForCurrentPayment(payment, currentPayment);

		return paymentRepository.save(currentPayment);
	}

	@Transactional
	public void remove(Long id) {
		try {
			paymentRepository.deleteById(id);
			paymentRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new PaymentNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_PAYMENT_IN_USE, id));
		}
	}
}