package com.rentACar.rentACar.services.dtos.responses.Rental;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GetRentalResponse {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private int startKilometer;
    private int endKilometer;
    private double totalPrice;
    private double discount;
}
