package com.rentACar.rentACar.services.dtos.requests.Discount;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddDiscountRequest {
    private String name;
    private Float rate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String code;
    @Nullable
    private int userId;


}
