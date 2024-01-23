package com.rentACar.rentACar.services.dtos.requests.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomer {
    private String email;
    private String password;
    private String companyName;
    private String taxNo;

}
