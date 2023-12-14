package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipleValidator implements ConstraintValidator<Multiple, Number> {
	private int multipleNumber;
	
	@Override
	public void initialize(Multiple constraintAnnotation) {
		this.multipleNumber = constraintAnnotation.number();
	}
	
	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		Boolean valid = Boolean.TRUE;
		
		if (value != null) {
			var decimalValue = BigDecimal.valueOf(value.doubleValue());
			var multipleDecimal = BigDecimal.valueOf(this.multipleNumber);
			var resto = decimalValue.remainder(multipleDecimal);
			
			valid = BigDecimal.ZERO.compareTo(resto) == 0;
		}
		
		return valid;
	}
}