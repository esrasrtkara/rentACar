package com.rentACar.rentACar.services.dtos.responses.Discount;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetDiscountListResponse {
    private String name;
    private Float rate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String code;
}
