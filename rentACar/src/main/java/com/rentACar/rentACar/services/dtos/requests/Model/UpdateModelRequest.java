package com.rentACar.rentACar.services.dtos.requests.Model;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateModelRequest {
    private int id;
    @Size(min = 2, message = "Model name cannot be less than 2 characters")
    private String name;
    private int brandId;
}
