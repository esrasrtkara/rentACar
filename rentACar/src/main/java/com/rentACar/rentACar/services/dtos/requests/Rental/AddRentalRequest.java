package com.rentACar.rentACar.services.dtos.requests.Rental;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AddRentalRequest {
    @FutureOrPresent(message = "The start date cannot be later than today.")
    private LocalDate startDate;
    private LocalDate endDate;
    @Nullable
    private String discountCode;
    private int carId;
}
