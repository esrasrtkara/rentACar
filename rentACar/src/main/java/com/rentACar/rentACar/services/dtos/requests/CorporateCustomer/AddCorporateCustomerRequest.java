package com.rentACar.rentACar.services.dtos.requests.CorporateCustomer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AddCorporateCustomerRequest {
    @Size(min = 2,message = "company name must have at least 2 letters")
    @NotNull
    private String companyName;
    @Size(min = 10,max = 10,message = "tax number must be 10 digits")
    @NotNull
    private String taxNo;
    private int userId;
}
