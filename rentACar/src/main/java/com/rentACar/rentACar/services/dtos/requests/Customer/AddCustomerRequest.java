package com.rentACar.rentACar.services.dtos.requests.Customer;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddCustomerRequest {
    @Size(min = 11 ,max = 11 , message = "Nationality ID must consist of 11 digits")
    private String nationalityId;
}
