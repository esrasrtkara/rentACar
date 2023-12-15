package com.rentACar.rentACar.services.dtos.requests.Brand;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddBrandRequest {
    @Size(min = 2 , message = "Marka adı 2 karakterden az olamaz!")
    private String name;
}
