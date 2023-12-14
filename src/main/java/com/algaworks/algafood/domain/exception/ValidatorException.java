package com.algaworks.algafood.domain.exception;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidatorException extends RuntimeException {
	
	private BindingResult bindingResult;
}