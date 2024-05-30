package com.algaworks.algafood.api.mapper;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.request.ProductRequest;
import com.algaworks.algafood.api.model.response.ProductResponse;
import com.algaworks.algafood.domain.model.Product;

@Component
public class ProductMapper {
	@Autowired
	private ModelMapper modelMapper;

	public Product productRequestForProduct(ProductRequest productRequest) {
		return modelMapper.map(productRequest, Product.class);
	}

	public void copyProductForCurrentProduct(Product product, Product currentProduct) {
		modelMapper.map(product, currentProduct);
	}

	public ProductResponse productForProductResponse(Product product) {
		return modelMapper.map(product, ProductResponse.class);
	}

	public List<ProductResponse> productListForProductListResponse(Collection<Product> product) {
		return modelMapper.map(product, new TypeToken<List<ProductResponse>>(){}.getType());
	}
}