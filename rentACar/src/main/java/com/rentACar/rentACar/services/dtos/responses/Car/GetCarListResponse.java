package com.rentACar.rentACar.services.dtos.responses.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarListResponse {
    private int kilometer;
    private int year;
    private String plate;
    private double dailyPrice;
}
