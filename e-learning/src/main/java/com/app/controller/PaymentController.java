package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.Payment;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/make-payment")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        Payment newPayment = paymentService.makePayment(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }
}
