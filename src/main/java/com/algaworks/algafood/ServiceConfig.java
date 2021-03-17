package com.algaworks.algafood;

import com.algaworks.algafood.di.service.CustomerActivationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
  @Bean(initMethod = "init", destroyMethod = "destroy")
  public CustomerActivationService customerActivationService() {
    return new CustomerActivationService();
  }
}