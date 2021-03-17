package com.algaworks.algafood;

import com.algaworks.algafood.di.model.Customer;
import com.algaworks.algafood.di.service.CustomerActivationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstController {
	@Autowired
	private CustomerActivationService customerActivationService;
	
	public MyFirstController(CustomerActivationService ativacaoClienteService) {
		this.customerActivationService = ativacaoClienteService;
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Customer customer = new Customer("João", "joao@xyz.com", "3499998888");
		
		customerActivationService.activate(customer);
		
		return "Hello!";
	}
}