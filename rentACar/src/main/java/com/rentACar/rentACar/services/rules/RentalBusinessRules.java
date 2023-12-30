package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.repositories.RentalRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;
    private final CarService carService;

    public  void checkIfEndDateBeforeStartDate(LocalDate startDate,LocalDate endDate){
        if (endDate.isBefore(startDate)){
            throw new RuntimeException("The end date cannot be later than the start date.");
        }
    }
    public void checkIfCarId(int carId){
        if (!carService.controlCarId(carId)) {
            throw new RuntimeException("Car ID not found in database");
        }
    }
    public void check25Day(LocalDate startDate,LocalDate endDate){
        if (ChronoUnit.DAYS.between(startDate,endDate) > 25) {
            throw new RuntimeException("A vehicle can be rented for a maximum of 25 days.");
        }
    }

}
