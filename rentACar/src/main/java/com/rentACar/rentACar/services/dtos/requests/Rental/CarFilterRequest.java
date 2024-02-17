package com.rentACar.rentACar.services.dtos.requests.Rental;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarFilterRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    @Nullable
    private String discountCode;
    private int carId;
}
