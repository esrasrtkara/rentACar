package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.dtos.requests.Model.AddModelRequest;
import com.rentACar.rentACar.services.dtos.requests.Model.UpdateModelRequest;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelListResponse;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelResponse;

import java.util.List;

public interface ModelService {
    DataResult<List<GetModelListResponse>> getAll();
    DataResult<GetModelResponse> getById(int id);
    Result add(AddModelRequest request);
    Result update(UpdateModelRequest request);
    Result delete(int id);
    boolean controlModelId(int id);
}
