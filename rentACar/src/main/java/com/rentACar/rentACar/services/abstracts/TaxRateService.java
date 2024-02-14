package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.TaxRate.AddTaxRateRequest;
import com.rentACar.rentACar.services.dtos.requests.TaxRate.UpdateTaxRateRequest;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateListResponse;

import java.util.List;

public interface TaxRateService {
    List<GetTaxRateListResponse> getAll();
    GetTaxRateByIdResponse getById(int id);
    void add(AddTaxRateRequest request);
    void update(UpdateTaxRateRequest request);
    void delete(int id);
}
