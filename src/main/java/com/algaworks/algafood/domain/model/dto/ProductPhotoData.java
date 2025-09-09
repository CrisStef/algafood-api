package com.algaworks.algafood.domain.model.dto;

import com.algaworks.algafood.api.model.request.ProductPhotoRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Getter
@Builder
public class ProductPhotoData {
    private ProductPhotoRequest photoRequest;
    private Long restaurantId;
    private Long productId;
}