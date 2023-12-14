package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.entities.Model;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelListResponse;

import java.util.List;

public interface ModelService {
    List<GetModelListResponse> getAll();
    boolean controlModelId(int id);
}
