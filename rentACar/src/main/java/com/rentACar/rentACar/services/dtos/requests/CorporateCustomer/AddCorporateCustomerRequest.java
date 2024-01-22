package com.rentACar.rentACar.services.dtos.requests.CorporateCustomer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AddCorporateCustomerRequest {
    private String companyName;
    private String taxNo;
    private int userId;
}
