package com.rentACar.rentACar.services.dtos.requests.Customer;

import com.rentACar.rentACar.entities.concretes.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AddCustomerRequest {
    @Size(min = 1 , message = "The firstname must be at least one letter")
    @NotNull
    private String firstName;
    @Size(min = 1 , message = "The lastname must be at least one letter")
    @NotNull
    private String lastName;
    @NotNull
    private int userId;
}
