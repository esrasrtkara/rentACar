package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.UpdateCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {

    List<GetCorporateCustomerListResponse> getAll();
    GetCorporateCustomerResponse getById(int id);
    public void add(AddCorporateCustomerRequest request);
    public void update(UpdateCorporateCustomerRequest request);

    public void delete(int id);
}
