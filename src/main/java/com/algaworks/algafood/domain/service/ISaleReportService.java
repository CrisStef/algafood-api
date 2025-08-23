package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.filter.DailySaleFilter;

public interface ISaleReportService {
    byte[] generateDailySalesReport(DailySaleFilter filter, String timeOffset);
}