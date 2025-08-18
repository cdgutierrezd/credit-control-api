package com.project.credit_control.service.interfaces;

import com.project.credit_control.model.dto.client.ClientResponse;
import com.project.credit_control.model.dto.client.SaveClienteRequest;
import com.project.credit_control.model.entity.Client;

import java.math.BigDecimal;
import java.util.List;

public interface IClientService {
    List<ClientResponse> findAll();

    List<ClientResponse> findByDebt();

    ClientResponse findById(Integer id);

    ClientResponse save(SaveClienteRequest request);

    ClientResponse update(Integer id, SaveClienteRequest request);

    void delete(Integer id);

    BigDecimal calculatedDebt(Integer id);

    Client clientExists(Integer id);
}
