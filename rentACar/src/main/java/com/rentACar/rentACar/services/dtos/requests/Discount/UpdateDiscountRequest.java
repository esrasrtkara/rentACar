package com.rentACar.rentACar.services.dtos.requests.Discount;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDiscountRequest {
    private int id;
    private String name;
    private Float rate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String code;
    private int userId;
    private int carId;
}
