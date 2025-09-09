package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.mapper.ProductPhotoMapper;
import com.algaworks.algafood.api.model.response.ProductPhotoResponse;
import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.ProductPhoto;
import com.algaworks.algafood.domain.model.dto.NewPhoto;
import com.algaworks.algafood.domain.model.dto.ProductPhotoData;
import com.algaworks.algafood.domain.repository.ProductRepository;
import com.algaworks.algafood.domain.util.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ProductPhotoCatalogService {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPhotoMapper productPhotoMapper;
    @Autowired
    private IPhotoStorageService photoStorageService;

    public ProductPhotoResponse createProductPhoto(ProductPhotoData productPhotoData) throws IOException {
        Product product = productService.getRestaurantProduct(
                productPhotoData.getRestaurantId(), productPhotoData.getProductId());
        MultipartFile fileData = productPhotoData.getPhotoRequest().getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(productPhotoData.getPhotoRequest().getDescription());
        photo.setContentType(fileData.getContentType());
        photo.setFileSize(fileData.getSize());
        photo.setFileName(fileData.getOriginalFilename());

        return productPhotoMapper.productPhotoForProductPhotoResponse(
                this.saveProductPhoto(photo, fileData.getInputStream()));
    }

    @Transactional
    public ProductPhoto saveProductPhoto(ProductPhoto productPhoto, InputStream fileData) {
        Long restaurantId = productPhoto.getRestaurantId();
        Long productId = productPhoto.getProduct().getId();

        Optional<ProductPhoto> existingPhoto =
                productRepository.findPhotoById(restaurantId, productId);

        if (existingPhoto.isPresent()) {
            productRepository.delete(existingPhoto.get());
        }

        String fileName = photoStorageService.generateFileName(productPhoto.getFileName());

        productPhoto.setFileName(FileNameUtils.removeExtension(fileName));
        productPhoto = productRepository.save(productPhoto);
        productRepository.flush();

        NewPhoto newPhoto = NewPhoto.builder()
                 .fileName(fileName)
                 .inputStream(fileData)
                 .build();

        photoStorageService.store(newPhoto);

        return productPhoto;
    }
}