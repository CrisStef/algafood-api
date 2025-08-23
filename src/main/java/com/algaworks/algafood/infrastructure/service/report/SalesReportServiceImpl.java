package com.algaworks.algafood.infrastructure.service.report;

import com.algaworks.algafood.domain.exception.ReportException;
import com.algaworks.algafood.domain.filter.DailySaleFilter;
import com.algaworks.algafood.domain.service.ISaleQueryService;
import com.algaworks.algafood.domain.service.ISaleReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class SalesReportServiceImpl implements ISaleReportService {
    @Autowired
    private ISaleQueryService saleQueryService;

    @Override
    public byte[] generateDailySalesReport(DailySaleFilter filter, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream(
                    "/reports/daily-sales.jasper");
            var params = new HashMap<String, Object>();
            params.put("REPORT_LOCALE", new Locale("pt", "BR"));

            var dailySales = saleQueryService.findDailySale(filter, timeOffset);
            var dataSource = new JRBeanCollectionDataSource(dailySales);

            var jasperPrint = JasperFillManager.fillReport(inputStream, params, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi posível emitir relatório de vendas diárias", e);
        }
    }
}