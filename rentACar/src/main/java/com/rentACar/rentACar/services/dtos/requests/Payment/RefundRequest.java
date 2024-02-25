package com.rentACar.rentACar.services.dtos.requests.Payment;

import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.net.RequestOptions;
import lombok.Data;

import java.util.Collections;


@Data
public class RefundRequest {
    private String chargeId;
    private int amount;

    public Refund createRefund() throws StripeException {
        return Refund.create(
                Collections.singletonMap("charge", chargeId),
                RequestOptions.builder().build()
        );
    }

}
