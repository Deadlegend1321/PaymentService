package com.mudit.paymentservice.paymentgateways;

public interface PaymentGateway {

    String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount);
}
