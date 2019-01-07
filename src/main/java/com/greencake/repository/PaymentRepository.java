package com.greencake.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greencake.domain.Payment;


public interface PaymentRepository extends JpaRepository<Payment, String> {
    Payment findPaymentByTradeNo(String tradeNo);

    List<Payment> findPaymentsByCreationDateBetween(Date from, Date to);
}
