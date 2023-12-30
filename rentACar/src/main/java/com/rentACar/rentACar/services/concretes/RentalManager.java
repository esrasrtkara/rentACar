package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.RentalRepository;
import com.rentACar.rentACar.services.abstracts.*;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalListResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalResponse;
import com.rentACar.rentACar.services.rules.RentalBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final RentalBusinessRules rentalBusinessRules;


    @Override
    public List<GetRentalListResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetRentalListResponse> responses = rentals.stream().map(rental -> modelMapperService.forResponse()
                .map(rental, GetRentalListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetRentalResponse getById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public void add(AddRentalRequest request) {
       // Long totalDay = ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate());
            this.rentalBusinessRules.checkIfEndDateBeforeStartDate(request.getStartDate(),request.getEndDate());
            this.rentalBusinessRules.checkIfCarId(request.getCarId());
            this.rentalBusinessRules.check25Day(request.getStartDate(),request.getEndDate());
            Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
            rental.setStartKilometer(carService.carKilometer(request.getCarId()));
           /* double dailyPrice = carService.carDailyPrice(request.getCar().getId());
            double price = dailyPrice * (double) totalDay;
            double discount = request.getDiscount();
            double discountPrice = price * discount;
            double totalPrice = price - discountPrice;*/
            rentalRepository.save(rental);

    }

    @Override
    public void update(UpdateRentalRequest request) {
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        Rental rentalToDelete = rentalRepository.findById(id).orElseThrow();
        rentalRepository.delete(rentalToDelete);
    }
}