package com.mudit.paymentservice.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripePaymentGateway implements PaymentGateway{

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount) {
        Stripe.apiKey = stripeApiKey;

        ProductCreateParams params =
                ProductCreateParams.builder().setName("Gold Plan").build();
        Product product = null;
        try {
            product = Product.create(params);
        } catch (StripeException e) {
            System.out.println(e);
            //throw new RuntimeException(e);
        }

        PriceCreateParams params1 =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setId(product.getId()).build()
                                //PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();
        Price price = null;
        try {
            price = Price.create(params1);
        } catch (StripeException e) {
            System.out.println(e);
            //throw new RuntimeException(e);
        }

        Map<String, Object> redirect = new HashMap<>();
        redirect.put("return_url", "https://example.com/success");

        Map<String, Object> after_completion = new HashMap<>();
        after_completion.put("type", redirect);
        after_completion.put("redirect", redirect);

        PaymentLinkCreateParams params2 =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .putAllExtraParam(after_completion)
                                        .build()
                        )
                        .build();


        PaymentLink paymentLink = null;
        try {
            paymentLink = PaymentLink.create(params2);
        } catch (StripeException e) {
            System.out.println(e);
            //throw new RuntimeException(e);
        }
        return paymentLink.getUrl();
    }
}
