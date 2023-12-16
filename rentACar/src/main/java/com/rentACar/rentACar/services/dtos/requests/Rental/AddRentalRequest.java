package com.rentACar.rentACar.services.dtos.requests.Rental;

import com.rentACar.rentACar.services.dtos.requests.Car.AddCarIdRequest;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserIdRequest;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AddRentalRequest {
    @FutureOrPresent(message = "The start date cannot be later than today.")
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate returnDate;
    private int startKilometer;
    private int endKilometer;
    private double totalPrice;
    private double discount;
    private AddCarIdRequest car;
    private AddUserIdRequest user;
}
