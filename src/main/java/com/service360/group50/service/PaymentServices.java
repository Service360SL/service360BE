package com.service360.group50.service;

import com.service360.group50.entity.Payments;
import com.service360.group50.repo.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    @Autowired
    private PaymentsRepository paymentsRepository;

    public Payments savePayment(Payments payment) {
        return paymentsRepository.save(payment);
    }
}
