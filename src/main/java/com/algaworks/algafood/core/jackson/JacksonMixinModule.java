package com.algaworks.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.mixin.ProductMixin;
import com.algaworks.algafood.api.model.mixin.SaleOrderMixin;
import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.SaleOrder;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {
	public JacksonMixinModule() {
		setMixInAnnotation(Product.class, ProductMixin.class);
		setMixInAnnotation(SaleOrder.class, SaleOrderMixin.class);
	}
}
