package com.rentACar.rentACar.services.dtos.requests.Customer;

import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private int id;
    private String nationalityId;
}
