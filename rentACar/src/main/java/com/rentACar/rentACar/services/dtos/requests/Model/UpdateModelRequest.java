package com.rentACar.rentACar.services.dtos.requests.Model;

import com.rentACar.rentACar.services.dtos.requests.Brand.AddBrandIdRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UpdateModelRequest {
    private int id;
    private String name;
    private AddBrandIdRequest brand;
}
