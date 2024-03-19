package com.mudit.paymentservice.paymentgateways;

public class StripePaymentGateway implements PaymentGateway{
    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        return null;
    }
}
