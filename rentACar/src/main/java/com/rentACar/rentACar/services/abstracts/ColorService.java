package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.dtos.requests.Color.AddColorRequest;
import com.rentACar.rentACar.services.dtos.requests.Color.UpdateColorRequest;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorListResponse;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorResponse;

import java.util.List;

public interface ColorService {
    DataResult<List<GetColorListResponse>> getAll();
    DataResult<GetColorResponse> getById(int id);
    Result add(AddColorRequest request);
    Result update(UpdateColorRequest request);
    Result delete(int id);
    boolean controlColorId(int id);
}
