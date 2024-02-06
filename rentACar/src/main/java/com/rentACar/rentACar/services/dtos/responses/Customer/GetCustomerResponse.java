package com.rentACar.rentACar.services.dtos.responses.Customer;

import lombok.Data;

@Data
public class GetCustomerResponse {
    private int id;
    private String firstName;
    private String lastName;
    private int UserId;
}
