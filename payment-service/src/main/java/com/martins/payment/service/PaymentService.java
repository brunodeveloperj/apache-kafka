package com.martins.payment.service;

import com.martins.payment.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
