package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.Rental;
import com.rentACar.rentACar.repositories.RentalRepository;
import com.rentACar.rentACar.services.abstracts.*;
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
    private CustomerService customerService;
    private EmployeeService employeeService;

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
        Long totalDay = ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate());
        if (request.getEndDate().isBefore(request.getStartDate())){
            throw new RuntimeException("The end date cannot be later than the start date.");
        } else if (!carService.controlCarId(request.getCar().getId())) {
            throw new RuntimeException("Car ID not found in database");
        } else if (!customerService.controlCustomerUserId(request.getCustomer().getId())) {
            throw new RuntimeException("Customer's User ID not found in database");
        } else if (!employeeService.controlEmployeeUserId(request.getEmployee().getId())) {
            throw new RuntimeException("Employee's User ID not found in database");
        } else if (ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate()) > 25) {
            throw new RuntimeException("A vehicle can be rented for a maximum of 25 days.");
        } else {
            Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
            rental.setStartKilometer(carService.carKilometer(request.getCar().getId()));
            double dailyPrice = carService.carDailyPrice(request.getCar().getId());
            double price = dailyPrice * (double) totalDay;
            double discount = request.getDiscount();
            double discountPrice = price * discount;
            double totalPrice = price - discountPrice;
            rental.setTotalPrice(totalPrice);
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