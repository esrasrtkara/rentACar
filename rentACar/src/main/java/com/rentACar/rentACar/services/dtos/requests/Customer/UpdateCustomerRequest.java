package com.rentACar.rentACar.services.dtos.requests.Customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private int id;
    @Size(min = 1 , message = "The firstname must be at least one letter")
    @NotNull
    private String firstName;
    @Size(min = 1 , message = "The lastname must be at least one letter")
    @NotNull
    private String lastName;
    @NotNull
    private int userId;
}
