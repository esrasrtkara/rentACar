package com.rentACar.rentACar.services.dtos.requests.TaxRate;

import lombok.Data;

@Data
public class AddTaxRateRequest {
    private String name;
    private Float rate;
}
