package com.rentACar.rentACar.services.dtos.responses.Customer;

import lombok.Data;

@Data
public class GetCustomerName {
    private int userId;
    private String firstName;
}
