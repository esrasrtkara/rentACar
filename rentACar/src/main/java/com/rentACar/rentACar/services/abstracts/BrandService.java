package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.dtos.requests.Brand.AddBrandRequest;
import com.rentACar.rentACar.services.dtos.requests.Brand.UpdateBrandRequest;
import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandListResponse;
import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandResponse;

import java.util.List;

public interface BrandService {
    DataResult<List<GetBrandListResponse>> getAll();
    DataResult<GetBrandResponse> getById(int id);
    Result add(AddBrandRequest request);
    Result update(UpdateBrandRequest request);
    Result delete(int id);
    boolean controlBrandId(int id);
}
