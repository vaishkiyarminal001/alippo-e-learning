package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Payment;
import com.app.repo.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Override
    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

}
