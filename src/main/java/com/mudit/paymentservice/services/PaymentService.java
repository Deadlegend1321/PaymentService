package com.mudit.paymentservice.services;

import com.mudit.paymentservice.paymentgateways.PaymentGateway;
import com.mudit.paymentservice.paymentgateways.PaymentGatewayStrategyChooser;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGatewayStrategyChooser paymentGatewayStrategyChooser;

    public PaymentService(PaymentGatewayStrategyChooser paymentGatewayStrategyChooser) {
        this.paymentGatewayStrategyChooser = paymentGatewayStrategyChooser;
    }

    public String initiatePayment(String orderId, String email, String phoneNumber, Long amount) {
        // Order order = orderService.getOrderDetails(orderId)
        // Long amount = order.getAmount();
        // double amount = 10.10; // store number * 100
        // String orderId, String email, String phoneNumber, Long amount
        // Long amount = 1010L; // 10.00 => 1000

        PaymentGateway paymentGateway = paymentGatewayStrategyChooser.getBestPaymentGateway();
        return "Payment initiated successfully";
    }
}
