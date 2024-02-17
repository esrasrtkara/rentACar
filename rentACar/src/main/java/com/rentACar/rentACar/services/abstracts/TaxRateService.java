package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.dtos.requests.TaxRate.AddTaxRateRequest;
import com.rentACar.rentACar.services.dtos.requests.TaxRate.UpdateTaxRateRequest;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateListResponse;

import java.util.List;

public interface TaxRateService {
    DataResult<List<GetTaxRateListResponse>> getAll();
    DataResult<GetTaxRateByIdResponse> getById(int id);
    Result add(AddTaxRateRequest request);
    Result update(UpdateTaxRateRequest request);
    Result delete(int id);
}
