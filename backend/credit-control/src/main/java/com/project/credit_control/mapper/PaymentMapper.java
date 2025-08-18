package com.project.credit_control.mapper;

import com.project.credit_control.model.dto.payment.PaymentResponse;
import com.project.credit_control.model.dto.payment.SavePaymentRequest;
import com.project.credit_control.model.dto.sale.SaleResponse;
import com.project.credit_control.model.dto.sale.SaveSaleRequest;
import com.project.credit_control.model.entity.Client;
import com.project.credit_control.model.entity.Payment;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentMapper {
    private final ModelMapper modelMapper;

    public PaymentResponse toResponse(Payment payment){
        PaymentResponse response = modelMapper.map(payment,PaymentResponse.class);
        response.setClientId(payment.getClient().getId());
        response.setClientName(payment.getClient().getName());
        return response;
    }

    public Payment toEntity(SavePaymentRequest request){
        Payment payment = modelMapper.map(request,Payment.class);
        Client client = Client.builder()
                .id(request.getClientId())
                .build();
        payment.setClient(client);
        return payment;
    }
}
