package com.project.credit_control.controller;

import com.project.credit_control.model.dto.client.ClientResponse;
import com.project.credit_control.model.dto.client.SaveClienteRequest;
import com.project.credit_control.service.interfaces.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/clients")
@AllArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll(){
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/debts")
    public ResponseEntity<List<ClientResponse>> findByDebt(){
        return ResponseEntity.ok(clientService.findByDebt());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> save(@RequestBody SaveClienteRequest request){
        return new ResponseEntity<>(clientService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Integer id
            ,@RequestBody SaveClienteRequest request){
        return new ResponseEntity<>(clientService.update(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/debt/{id}")
    public ResponseEntity<BigDecimal> findClientDebt(@PathVariable Integer id){
        return ResponseEntity.ok(clientService.calculatedDebt(id));
    }
}
