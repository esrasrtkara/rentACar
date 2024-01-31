package com.rentACar.rentACar.services.dtos.requests.Charge;

import lombok.Data;

@Data
public class ChargeRequest {
    public enum Currency {
        EUR, USD;
    }
    private String description;
    private int amount; // cents
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;

}
