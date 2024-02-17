package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.UpdateCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {

    DataResult<List<GetCorporateCustomerListResponse>> getAll();
    DataResult<GetCorporateCustomerResponse> getById(int id);
    public Result add(AddCorporateCustomerRequest request);
    public Result update(UpdateCorporateCustomerRequest request);

    public Result delete(int id);
}
