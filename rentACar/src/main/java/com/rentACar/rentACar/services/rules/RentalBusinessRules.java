package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.RentalRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.abstracts.DiscountService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final UserService userService;
    private final DiscountService discountService;

    public  void checkIfEndDateBeforeStartDate(LocalDate startDate,LocalDate endDate){
        if (endDate.isBefore(startDate)){
            throw new RuntimeException(Messages.CHECK_IF_END_DATE_FOR_STARTDATE);
        }
    }
    public void checkIfCarId(int carId){
        if (!carService.controlCarId(carId)) {
            throw new RuntimeException(Messages.CHECK_IF_CAR_ID);
        }
    }
    public void check25Day(LocalDate startDate,LocalDate endDate){
        if (ChronoUnit.DAYS.between(startDate,endDate) > 25) {
            throw new RuntimeException(Messages.CHECK_IF_25_DAY);
        }
    }

}
