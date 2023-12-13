package com.rentACar.rentACar.services.dtos.responses.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class GetCarResponse {
    private int id;
    private int kilometer;
    private int year;
    private String plate;
    private double dailyPrice;
}
