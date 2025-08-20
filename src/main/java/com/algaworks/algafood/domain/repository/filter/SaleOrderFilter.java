package com.algaworks.algafood.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;

@Getter
@Setter
public class SaleOrderFilter {
    private Long customerId;
    private Long restaurantId;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime startRegistrationDate;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime endRegistrationDate;
}
