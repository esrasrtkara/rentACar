package com.rentACar.rentACar.services.dtos.responses.Rental;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetCarFilterResponse {
    private int id;
    private String carStatus;
    private Float totalPrice;
    private Float discount;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private int startKilometer;
    private int endKilometer;
    private int carId;
    private int userId;
    private String discountCode;
}
