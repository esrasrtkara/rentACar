package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Customer;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerResponse;

import java.util.List;

public interface CustomerService {
    DataResult<List<GetCustomerListResponse>> getAll();
    DataResult<GetCustomerResponse> getById(int id);
    Result add(AddCustomerRequest request);
    Result update(UpdateCustomerRequest request);
    Result delete(int id);

    public Customer getCustomer();




}
