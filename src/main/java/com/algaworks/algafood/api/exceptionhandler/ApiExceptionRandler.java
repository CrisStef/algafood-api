package com.algaworks.algafood.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionRandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		Problem problem = createProblemBuilder(status, ProblemType.ENTITY_NOT_FOUND, e.getMessage()).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusiness(BusinessException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		Problem problem = createProblemBuilder(status, ProblemType.BUSINESS_ERROR, e.getMessage()).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> handleEntityInUse(EntityInUseException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;

		Problem problem = createProblemBuilder(status, ProblemType.ENTITY_IN_USE, e.getMessage()).build();

		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		} else if (body instanceof String) {
			body = Problem.builder()
				.title((String) body)
				.status(status.value())
				.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.INCOMPREENSIBLE_MESSAGE;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				e.getName(), e.getValue(), e.getRequiredType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, ProblemType.INVALID_PARAMETER, detail).build();
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.INCOMPREENSIBLE_MESSAGE;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ProblemType problemType = ProblemType.INCOMPREENSIBLE_MESSAGE;
		String detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", ex.getPropertyName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
					.status(status.value())
					.type(problemType.getUri())
					.title(problemType.getTitle())
					.detail(detail);
	}

	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}
}