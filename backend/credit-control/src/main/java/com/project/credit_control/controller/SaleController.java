package com.project.credit_control.controller;
import com.project.credit_control.model.dto.sale.SaleResponse;
import com.project.credit_control.model.dto.sale.SaveSaleRequest;
import com.project.credit_control.service.interfaces.ISaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SaleController {
    private final ISaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleResponse>> findAll(){
        return ResponseEntity.ok(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(saleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SaleResponse> save(@RequestBody SaveSaleRequest request){
        return new ResponseEntity<>(saleService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> update(@PathVariable Integer id
            ,@RequestBody SaveSaleRequest request){
        return new ResponseEntity<>(saleService.update(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        saleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
