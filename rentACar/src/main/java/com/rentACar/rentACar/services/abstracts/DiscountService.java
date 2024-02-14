package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddUserDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.UpdateDiscountRequest;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountListResponse;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    public List<GetDiscountListResponse> getAll();
    public GetDiscountByIdResponse getById(int id);
    public void add(AddDiscountRequest request);
    public void update(UpdateDiscountRequest request);
    public void delete(int id);


}
