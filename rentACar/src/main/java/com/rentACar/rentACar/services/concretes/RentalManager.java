package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Invoice;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.RentalRepository;
import com.rentACar.rentACar.services.abstracts.*;
import com.rentACar.rentACar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalListResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalResponse;
import com.rentACar.rentACar.services.rules.RentalBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final InvoiceService invoiceService;
    private final UserService userService;
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
    public GetRentalResponse add(AddRentalRequest request) {
            Long totalDay = ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate());
            this.rentalBusinessRules.checkIfEndDateBeforeStartDate(request.getStartDate(),request.getEndDate());
            this.rentalBusinessRules.checkIfCarId(request.getCarId());
            this.rentalBusinessRules.check25Day(request.getStartDate(),request.getEndDate());
            Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
            rental.setStartKilometer(carService.carKilometer(request.getCarId()));
            rental.setUser(userService.userEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
            Float price = carService.carDailyPrice(request.getCarId())*totalDay;
            rentalRepository.save(rental);
            invoiceService.add(totalDay,price,rental);
            GetRentalResponse response = modelMapperService.forResponse().map(rental,GetRentalResponse.class);
            return  response;
    }

    @Override
    public void update(UpdateRentalRequest request) {
        Long totalDay = ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate());
        this.rentalBusinessRules.checkIfEndDateBeforeStartDate(request.getStartDate(),request.getEndDate());
        this.rentalBusinessRules.checkIfCarId(request.getCarId());
        this.rentalBusinessRules.check25Day(request.getStartDate(),request.getEndDate());
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        rental.setStartKilometer(carService.carKilometer(request.getCarId()));
        rental.setUser(userService.userEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Float price = carService.carDailyPrice(request.getCarId())*totalDay;
        rentalRepository.save(rental);
        invoiceService.add(totalDay,price,rental);
    }

    @Override
    public void delete(int id) {
        Rental rentalToDelete = rentalRepository.findById(id).orElseThrow();
        rentalRepository.delete(rentalToDelete);
    }

    public boolean controlRentalId(int id) {
        try {
            Rental rental=rentalRepository.findById(id).orElseThrow();
            return true;
        } catch (NoSuchFieldError e) {
            return false;
        }
    }

}