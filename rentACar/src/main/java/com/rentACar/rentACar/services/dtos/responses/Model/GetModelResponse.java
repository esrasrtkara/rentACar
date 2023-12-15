package com.rentACar.rentACar.services.dtos.responses.Model;


import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandListResponse;
import lombok.Data;

@Data
public class GetModelResponse {
    private int id;
    private String name;
    private GetBrandListResponse brand;
}
