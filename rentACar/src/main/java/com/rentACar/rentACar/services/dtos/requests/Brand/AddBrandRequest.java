package com.rentACar.rentACar.services.dtos.requests.Brand;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddBrandRequest {
    @Size(min = 2 , message = "Brand name cannot be less than 2 characters")
    private String name;
}
