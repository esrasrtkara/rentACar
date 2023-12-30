package com.rentACar.rentACar.services.dtos.requests.Rental;

import com.rentACar.rentACar.services.dtos.requests.Car.AddCarIdRequest;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerIdRequest;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserIdRequest;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Null;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AddRentalRequest {
    @FutureOrPresent(message = "The start date cannot be later than today.")
    private LocalDate startDate;
    private LocalDate endDate;
    @Nullable
    private LocalDate returnDate;
    @Nullable
    private int endKilometer;
    private double discount;
    private AddCarIdRequest car;
    private AddCustomerIdRequest customer;
}
