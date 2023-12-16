package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.Rental;
import com.rentACar.rentACar.repositories.RentalRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.abstracts.RentalService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalListResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private RentalRepository rentalRepository;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private UserService userService;

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
        if (request.getEndDate().isBefore(request.getStartDate())){
            throw new RuntimeException("The end date cannot be later than the start date.");
        } else if (!carService.controlCarId(request.getCar().getId())) {
            throw new RuntimeException("Car id db bulunamadı");
        } else if (!userService.controlUserId(request.getUser().getId())) {
            throw new RuntimeException("User id db bulunamadı");
        } else if (ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate()) > 25) {
            throw new RuntimeException("A vehicle can be rented for a maximum of 25 days.");
        } else {
            Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
            rentalRepository.save(rental);
        }
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
