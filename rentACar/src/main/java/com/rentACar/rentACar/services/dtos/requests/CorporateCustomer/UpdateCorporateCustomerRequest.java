package com.rentACar.rentACar.services.dtos.requests.CorporateCustomer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCorporateCustomerRequest {
    private int id;
    @Size(min = 2,message = "company name must have at least 2 letters")
    @NotNull
    private String companyName;
    @Size(min = 10,max = 10,message = "tax number must be 10 digits")
    @NotNull
    private String taxNo;
    private int userId;
}
