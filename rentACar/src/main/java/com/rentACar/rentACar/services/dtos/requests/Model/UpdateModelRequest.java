package com.rentACar.rentACar.services.dtos.requests.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateModelRequest {
    private int id;
    @Size(min = 2, message = "Model name cannot be less than 2 characters")
    @NotNull(message = "Model name cannot be null.")
    private String name;
    @NotNull(message = "Brand id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int brandId;
}
