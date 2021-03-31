package com.algaworks.algafood.domain.util;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.ReflectionUtils;

public class MergeMapper {
	public static void merge(Map<String, Object> originData, Object destinyData) {
		ObjectMapper objectMapper = new ObjectMapper();
		Class<?> someClass = destinyData.getClass();
		Object convertedData = objectMapper.convertValue(originData, someClass);

		originData.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(someClass, propertyName);
			field.setAccessible(true);

			Object newValue = ReflectionUtils.getField(field, convertedData);
			ReflectionUtils.setField(field, destinyData, newValue);
		});
	}
}