package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.filter.DailySaleFilter;
import com.algaworks.algafood.domain.model.dto.DailySale;

import java.util.List;

public interface ISaleQueryService {
    List<DailySale> findDailySale(DailySaleFilter filter, String timeOffset);
}