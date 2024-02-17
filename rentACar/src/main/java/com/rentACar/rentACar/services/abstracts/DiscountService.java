package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddUserDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.UpdateDiscountRequest;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountListResponse;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    public DataResult<List<GetDiscountListResponse>> getAll();
    public DataResult<GetDiscountByIdResponse> getById(int id);
    public Result add(AddDiscountRequest request);
    public Result update(UpdateDiscountRequest request);
    public Result delete(int id);


}
