package com.algaworks.algafood.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValueZeroDescriptionValidator.class })
public @interface ValueZeroDescription {
	String message() default "descrição obrigatória inválida";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String fieldValue();
	
	String fieldDescription();
	
	String mandatoryDescription();
}