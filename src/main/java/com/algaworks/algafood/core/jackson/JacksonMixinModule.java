package com.algaworks.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.mixin.SaleOrderMixin;
import com.algaworks.algafood.domain.model.SaleOrder;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {
	public JacksonMixinModule() {
		setMixInAnnotation(SaleOrder.class, SaleOrderMixin.class);
	}
}
