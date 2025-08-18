package com.project.credit_control.service.impl;

import com.project.credit_control.exceptions.ClientNotFoundException;
import com.project.credit_control.exceptions.InvalidParamentersException;
import com.project.credit_control.exceptions.SaleNotFoundException;
import com.project.credit_control.mapper.SaleMapper;
import com.project.credit_control.model.dto.sale.SaleResponse;
import com.project.credit_control.model.dto.sale.SaveSaleRequest;
import com.project.credit_control.model.entity.Client;
import com.project.credit_control.model.entity.Sale;
import com.project.credit_control.repository.ClientRepository;
import com.project.credit_control.repository.SaleRepository;
import com.project.credit_control.service.interfaces.ISaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaleService implements ISaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ClientRepository clientRepository;

    @Override
    public List<SaleResponse> findAll(){
        return saleRepository
                .findAll()
                .stream()
                .map(saleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SaleResponse findById(Integer id){
        return saleRepository
                .findById(id)
                .map(saleMapper::toResponse)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found"));
    }

    @Override
    public SaleResponse save(SaveSaleRequest request){
        //It is verified that the client exists
        Integer clientId = request.getClientId();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        Sale sale = saleMapper.toEntity(request);
        if (sale.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidParamentersException("Invalid Amount");
        }
        sale.setId(null);
        sale.setClient(client);
        sale.setDate(LocalDateTime.now());
        return saleMapper.toResponse(saleRepository.save(sale));
    }

    @Override
    public SaleResponse update(Integer id, SaveSaleRequest request){
        //It is verified that the sale exists
        Sale sale = saleRepository
                .findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found"));
        //It is verified that the client exists
        Integer clientId = request.getClientId();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        //the sale is updated
        sale.setClient(client);
        sale.setAmount(request.getAmount());
        sale.setDescription(request.getDescription());

        //save the sale
        Sale updateSale = saleRepository.save(sale);
        return saleMapper.toResponse(updateSale);
    }

    @Override
    public void delete(Integer id){
        Sale sale = saleRepository
                .findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found"));
        saleRepository.delete(sale);
    }
}
