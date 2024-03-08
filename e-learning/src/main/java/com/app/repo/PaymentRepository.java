package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
