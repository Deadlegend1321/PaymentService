package com.mudit.paymentservice.paymentgateways;

public class PaymentGatewayStrategyChooser {

    private RazorpayPaymentGateway razorpayPaymentGateway;

    private StripePaymentGateway stripePaymentGateway;

    public PaymentGatewayStrategyChooser(RazorpayPaymentGateway razorpayPaymentGateway, StripePaymentGateway stripePaymentGateway) {
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getBestPaymentGateway() {
        return razorpayPaymentGateway;
    }
}
