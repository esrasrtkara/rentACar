package com.rentACar.rentACar.services.dtos.requests.Brand;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddBrandRequest {
    @NotNull(message = "Brand name cannot be null.")
    @Size(min = 2 , message = "Brand name cannot be less than 2 characters")
    private String name;
    private MultipartFile  logoPath;
}
