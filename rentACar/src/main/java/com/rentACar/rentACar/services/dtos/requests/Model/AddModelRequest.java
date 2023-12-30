package com.rentACar.rentACar.services.dtos.requests.Model;

import com.rentACar.rentACar.services.dtos.requests.Brand.AddBrandIdRequest;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class AddModelRequest {
    @Size(min = 2, message = "Model name cannot be less than 2 characters")
    private String name;
    private AddBrandIdRequest brand;
}
