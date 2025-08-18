package com.project.credit_control.mapper;

import com.project.credit_control.model.dto.sale.SaleResponse;
import com.project.credit_control.model.dto.sale.SaveSaleRequest;
import com.project.credit_control.model.entity.Client;
import com.project.credit_control.model.entity.Sale;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaleMapper {
    private final ModelMapper modelMapper;

    public SaleResponse toResponse(Sale sale){
        SaleResponse response = modelMapper.map(sale,SaleResponse.class);
        response.setClientId(sale.getClient().getId());
        response.setClientName(sale.getClient().getName());
        return response;
    }

    public Sale toEntity(SaveSaleRequest request){
        Sale sale = modelMapper.map(request,Sale.class);
        Client client = Client.builder()
                .id(request.getClientId())
                .build();
        sale.setClient(client);
        return sale;
    }
}
