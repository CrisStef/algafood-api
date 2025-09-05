package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.mapper.ProductPhotoMapper;
import com.algaworks.algafood.api.model.request.ProductPhotoRequest;
import com.algaworks.algafood.api.model.response.ProductPhotoResponse;
import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.ProductPhoto;
import com.algaworks.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class ProductPhotoCatalogService {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPhotoMapper productPhotoMapper;

    public ProductPhotoResponse createProductPhoto(ProductPhotoRequest photoRequest, Long restaurantId, Long productId) {
        Product product = productService.getRestaurantProduct(restaurantId, productId);
        MultipartFile file = photoRequest.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(photoRequest.getDescription());
        photo.setContentType(file.getContentType());
        photo.setFileSize(file.getSize());
        photo.setFileName(file.getName());

        return productPhotoMapper.productPhotoForProductPhotoResponse(
                this.saveProductPhoto(photo));
    }

    @Transactional
    public ProductPhoto saveProductPhoto(ProductPhoto productPhoto) {
        return productRepository.save(productPhoto);
    }
}