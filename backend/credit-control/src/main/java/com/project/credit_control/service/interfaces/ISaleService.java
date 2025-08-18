package com.project.credit_control.service.interfaces;

import com.project.credit_control.model.dto.sale.SaleResponse;
import com.project.credit_control.model.dto.sale.SaveSaleRequest;

import java.util.List;

public interface ISaleService {
    List<SaleResponse> findAll();

    SaleResponse findById(Integer id);

    SaleResponse save(SaveSaleRequest request);

    SaleResponse update(Integer id, SaveSaleRequest request);

    void delete(Integer id);
}
