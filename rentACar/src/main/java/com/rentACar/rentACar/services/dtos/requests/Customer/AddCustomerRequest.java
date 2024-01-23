package com.rentACar.rentACar.services.dtos.requests.Customer;

import com.rentACar.rentACar.entities.concretes.User;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCustomerRequest {
    @Size(min = 11 ,max = 11 , message = "Nationality ID must consist of 11 digits")
    private String firstName;
    private String lastName;
    private int userId;
}
