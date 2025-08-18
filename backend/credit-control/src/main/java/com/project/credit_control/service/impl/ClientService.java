package com.project.credit_control.service.impl;

import com.project.credit_control.exceptions.ClientNotFoundException;
import com.project.credit_control.mapper.ClientMapper;
import com.project.credit_control.model.dto.client.ClientResponse;
import com.project.credit_control.model.dto.client.SaveClienteRequest;
import com.project.credit_control.model.entity.Client;
import com.project.credit_control.repository.ClientRepository;
import com.project.credit_control.repository.PaymentRepository;
import com.project.credit_control.repository.SaleRepository;
import com.project.credit_control.service.interfaces.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final SaleRepository saleRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public List<ClientResponse> findAll(){
        return clientRepository
                .findAll()
                .stream()
                .map(client -> {
                    BigDecimal debt = calculatedDebt(client.getId());
                    return clientMapper.toResponse(client,debt);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientResponse> findByDebt(){
        return clientRepository
                .findAll()
                .stream()
                .map(client -> {
                    BigDecimal debt = calculatedDebt(client.getId());
                    return clientMapper.toResponse(client,debt);
                })
                .filter(clientResponse -> clientResponse.getDebt().compareTo(BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse findById(Integer id){
        return clientRepository
                .findById(id)
                .map(client -> {
                    BigDecimal debt = calculatedDebt(client.getId());
                    System.out.println("Debt: " + debt);
                    return clientMapper.toResponse(client,debt);
                })
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    @Override
    public ClientResponse save(SaveClienteRequest request){
        Client client = clientMapper.toEntity(request);
        Client saveClient = clientRepository.save(client);
        return clientMapper.toResponse(saveClient,BigDecimal.ZERO);
    }

    @Override
    public ClientResponse update(Integer id
            , SaveClienteRequest request){
        return clientRepository.findById(id)
                .map((client -> {
                    client.setName(request.getName());
                    client.setAddress(request.getAddress());
                    return clientRepository.save(client);
                }))
                .map(client -> {
                    BigDecimal debt = calculatedDebt(client.getId());
                    return clientMapper.toResponse(client,debt);
                })
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

    @Override
    public void delete(Integer id){
        Client client = clientExists(id);
        clientRepository.delete(client);
    }

    @Override
    public BigDecimal calculatedDebt(Integer id){
        Client client = clientExists(id);
        BigDecimal totalSales = saleRepository
                .sumAmountByClientId(id)
                .orElse(BigDecimal.ZERO);
        BigDecimal totalPayments = paymentRepository
                .sumAmountByClientId(id)
                .orElse(BigDecimal.ZERO);
        return totalSales.subtract(totalPayments);
    }


    @Override
    public Client clientExists(Integer id){
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

}
