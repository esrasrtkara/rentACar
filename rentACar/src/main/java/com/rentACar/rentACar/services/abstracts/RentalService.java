package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddUserDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.CarFilterRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetCarFilterResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalListResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalResponse;

import java.time.LocalDate;
import java.util.List;

public interface RentalService {
    DataResult<List<GetRentalListResponse>> getAll();
    DataResult<GetRentalResponse> getById(int id);
    DataResult<GetRentalResponse> add(AddRentalRequest request);
    Result update(UpdateRentalRequest request);
    Result delete(int id);
    public boolean controlRentalId(int id);

    public GetCarFilterResponse carFilter(CarFilterRequest request);
    public void userDiscount(AddUserDiscountRequest request);



}
