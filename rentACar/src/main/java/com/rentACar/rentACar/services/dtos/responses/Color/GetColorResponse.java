package com.rentACar.rentACar.services.dtos.responses.Color;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
public class GetColorResponse {
    private int id;
    private String name;
}
