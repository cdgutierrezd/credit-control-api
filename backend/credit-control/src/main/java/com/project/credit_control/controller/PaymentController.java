package com.project.credit_control.controller;

import com.project.credit_control.model.dto.payment.PaymentResponse;
import com.project.credit_control.model.dto.payment.SavePaymentRequest;
import com.project.credit_control.service.interfaces.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/payments")
@AllArgsConstructor
public class PaymentController {

    private final IPaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> save(@RequestBody SavePaymentRequest request){
        return new ResponseEntity<>(paymentService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> update(@PathVariable Integer id
            ,@RequestBody SavePaymentRequest request){
        return new ResponseEntity<>(paymentService.update(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        paymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
