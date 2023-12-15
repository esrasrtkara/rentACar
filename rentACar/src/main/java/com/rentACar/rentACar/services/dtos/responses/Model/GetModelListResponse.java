package com.rentACar.rentACar.services.dtos.responses.Model;

import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class GetModelListResponse {
    private String name;
    private GetBrandListResponse brand;
}
