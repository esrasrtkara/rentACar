package com.rentACar.rentACar.services.dtos.requests.TaxRate;

import lombok.Data;

@Data
public class UpdateTaxRateRequest {
    private int id;
    private String name;
    private Float rate;
}
