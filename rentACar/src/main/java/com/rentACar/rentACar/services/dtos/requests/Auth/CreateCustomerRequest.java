package com.rentACar.rentACar.services.dtos.requests.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
