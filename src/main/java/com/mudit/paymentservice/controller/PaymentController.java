package com.mudit.paymentservice.controller;

import com.mudit.paymentservice.dtos.InitiatePaymentDTO;
import com.mudit.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentDTO initiatePaymentDTO) {
        return paymentService.initiatePayment(initiatePaymentDTO.getOrderId(),
                initiatePaymentDTO.getEmail(),
                initiatePaymentDTO.getPhoneNumber(),
                initiatePaymentDTO.getAmount());
    }
}
