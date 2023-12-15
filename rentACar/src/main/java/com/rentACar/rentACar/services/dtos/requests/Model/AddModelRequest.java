package com.rentACar.rentACar.services.dtos.requests.Model;

import com.rentACar.rentACar.entities.Brand;
import com.rentACar.rentACar.services.dtos.requests.Brand.AddBrandIdRequest;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AddModelRequest {
    @Size(min = 2, message = "Model ismi 2 karakterden az olamaz!")
    private String name;
    private AddBrandIdRequest brand;
}
