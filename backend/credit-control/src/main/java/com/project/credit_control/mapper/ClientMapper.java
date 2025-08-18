package com.project.credit_control.mapper;

import com.project.credit_control.model.dto.client.ClientResponse;
import com.project.credit_control.model.dto.client.SaveClienteRequest;
import com.project.credit_control.model.entity.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class ClientMapper {
    private final ModelMapper mapper;

    public ClientResponse toResponse(Client client, BigDecimal debt){
        ClientResponse clientResponse = mapper.map(client,ClientResponse.class);
        clientResponse.setDebt(debt);
        return clientResponse;
    }

    public Client toEntity(SaveClienteRequest request){
        return mapper.map(request,Client.class);
    }

}
