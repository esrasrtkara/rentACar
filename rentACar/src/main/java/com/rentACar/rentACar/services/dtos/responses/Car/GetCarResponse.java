package com.rentACar.rentACar.services.dtos.responses.Car;

import lombok.Data;

@Data
public class GetCarResponse {
    private int id;
    private int kilometer;
    private int year;
    private String plate;
    private double dailyPrice;
}
