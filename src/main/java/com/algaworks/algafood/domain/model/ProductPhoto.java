package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ProductPhoto {
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "product_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Product product;
    private String fileName;
    private String description;
    private String contentType;
    private Long fileSize;

    public Long getRestaurantId() {
        if (getProduct() != null) {
            return getProduct().getRestaurant().getId();
        }

        return null;
    }
}