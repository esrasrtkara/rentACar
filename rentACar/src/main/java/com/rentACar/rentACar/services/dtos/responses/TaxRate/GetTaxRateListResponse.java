package com.rentACar.rentACar.services.dtos.responses.TaxRate;

import lombok.Data;

@Data
public class GetTaxRateListResponse {
    private String name;
    private Float rate;
}
