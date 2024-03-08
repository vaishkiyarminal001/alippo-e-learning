package com.app.service;

import org.springframework.stereotype.Service;

import com.app.models.Payment;

@Service
public interface PaymentService {
    
    Payment makePayment(Payment payment);
}
