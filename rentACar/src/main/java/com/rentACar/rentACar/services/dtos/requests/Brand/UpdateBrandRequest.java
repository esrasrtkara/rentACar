package com.rentACar.rentACar.services.dtos.requests.Brand;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateBrandRequest {
    private int id;
    @Size(min = 2 , message = "Brand name cannot be less than 2 characters")
    private String name;
    private MultipartFile logoPath;
}
