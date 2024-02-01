package com.rentACar.rentACar.services.dtos.requests.Brand;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBrandRequest {
    private int id;
    @Size(min = 2 , message = "Brand name cannot be less than 2 characters")
    private String name;
    private String logoPath;
}
