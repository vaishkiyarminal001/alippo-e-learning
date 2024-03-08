package com.app.service;

import java.util.List;

import com.app.exception.NotFoundException;
import com.app.models.Payment;


public interface PaymentService {
	Payment makePayment(Long userId, Long courseId, double amount) throws NotFoundException;

	List<Payment> getPaymentsByUser(Long userId) throws NotFoundException;

	List<Payment> getPaymentsByCourse(Long courseId) throws NotFoundException;
}
