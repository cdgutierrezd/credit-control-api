package com.project.credit_control.service.impl;

import com.project.credit_control.exceptions.ClientNotFoundException;
import com.project.credit_control.exceptions.InvalidParamentersException;
import com.project.credit_control.exceptions.PaymentNotFoundException;
import com.project.credit_control.mapper.PaymentMapper;
import com.project.credit_control.mapper.SaleMapper;
import com.project.credit_control.model.dto.payment.PaymentResponse;
import com.project.credit_control.model.dto.payment.SavePaymentRequest;
import com.project.credit_control.model.entity.Client;
import com.project.credit_control.model.entity.Payment;
import com.project.credit_control.repository.ClientRepository;
import com.project.credit_control.repository.PaymentRepository;
import com.project.credit_control.service.interfaces.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ClientRepository clientRepository;
    private final SaleMapper saleMapper;

    @Override
    public List<PaymentResponse> findAll(){
        return paymentRepository
                .findAll()
                .stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponse findById(Integer id){
        return paymentRepository
                .findById(id)
                .map(paymentMapper::toResponse)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    @Override
    public PaymentResponse save(SavePaymentRequest request){
        // Verify that the client exists
        Integer clientId = request.getClientId();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        Payment payment = paymentMapper.toEntity(request);
        if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidParamentersException("Invalid Amount");
        }
        payment.setClient(client);
        payment.setId(null);
        payment.setDate(LocalDateTime.now());
        Payment savePayment = paymentRepository.save(payment);
        return paymentMapper.toResponse(savePayment);
    }

    @Override
    public PaymentResponse update(Integer id
            , SavePaymentRequest request){
        // Verify that the client exists
        Client client = clientRepository
                .findById(request.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        //Verify that the payment exists
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        //Update payment information
        payment.setClient(client);
        payment.setAmount(request.getAmount());
        //Save and return updated payment
        Payment updatePayment = paymentRepository.save(payment);
        return paymentMapper.toResponse(updatePayment);
    }

    @Override
    public void delete(Integer id){
        //Verify that the payment exists
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        paymentRepository.delete(payment);
    }
}
