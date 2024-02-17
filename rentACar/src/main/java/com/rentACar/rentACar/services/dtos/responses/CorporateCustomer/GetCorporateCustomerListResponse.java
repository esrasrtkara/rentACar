package com.rentACar.rentACar.services.dtos.responses.CorporateCustomer;

import lombok.Data;

@Data
public class GetCorporateCustomerListResponse {
    private String companyName;
    private String taxNo;
    private int userId;
}
