package com.rentACar.rentACar.services.dtos.responses.CorporateCustomer;

import lombok.Data;

@Data
public class GetCorporateCustomerResponse {
    private int id;
    private String companyName;
    private String taxNo;
    private int userId;
}
