package com.project.credit_control.service.interfaces;

import com.project.credit_control.model.dto.payment.PaymentResponse;
import com.project.credit_control.model.dto.payment.SavePaymentRequest;

import java.util.List;

public interface IPaymentService {
    List<PaymentResponse> findAll();

    PaymentResponse findById(Integer id);

    PaymentResponse save(SavePaymentRequest request);

    PaymentResponse update(Integer id, SavePaymentRequest request);

    void delete(Integer id);
}
