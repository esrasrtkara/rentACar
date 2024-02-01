package com.rentACar.rentACar.services.dtos.requests.Color;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class UpdateColorRequest {
    private int id;
    @Size(min = 2 , message = "Color name cannot be less than 2 characters")
    private String name;
    private String code;
}
