package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalListResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalResponse;

import java.time.LocalDate;
import java.util.List;

public interface RentalService {
    List<GetRentalListResponse> getAll();
    GetRentalResponse getById(int id);
    GetRentalResponse add(AddRentalRequest request);
    void update(UpdateRentalRequest request);
    void delete(int id);
    public boolean controlRentalId(int id);

}
