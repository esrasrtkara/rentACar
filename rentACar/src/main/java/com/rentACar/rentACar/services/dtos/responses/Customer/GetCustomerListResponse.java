package com.rentACar.rentACar.services.dtos.responses.Customer;

import lombok.Data;

@Data
public class GetCustomerListResponse {
    private String firstName;
    private String lastName;
    private int UserId;
}
