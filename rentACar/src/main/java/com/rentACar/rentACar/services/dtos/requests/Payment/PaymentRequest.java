package com.rentACar.rentACar.services.dtos.requests.Payment;

import lombok.Data;

@Data
public class PaymentRequest {
    private String tokenId;
    private int amount;
    private String currency;

}
