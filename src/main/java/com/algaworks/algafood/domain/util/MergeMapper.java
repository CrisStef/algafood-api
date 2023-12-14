package com.algaworks.algafood.domain.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public class MergeMapper {
	public static void merge(Map<String, Object> originData, Object destinyData, HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Class<?> someClass = destinyData.getClass();
			Object convertedData = objectMapper.convertValue(originData, someClass);

			originData.forEach((propertyName, propertyValue) -> {
				Field field = ReflectionUtils.findField(someClass, propertyName);
				field.setAccessible(true);

				Object newValue = ReflectionUtils.getField(field, convertedData);
				ReflectionUtils.setField(field, destinyData, newValue);
			});
		} catch (Exception e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
}