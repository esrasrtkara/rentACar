package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.Color.AddColorRequest;
import com.rentACar.rentACar.services.dtos.requests.Color.UpdateColorRequest;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorListResponse;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorResponse;

import java.util.List;

public interface ColorService {
    List<GetColorListResponse> getAll();
    GetColorResponse getById(int id);
    void add(AddColorRequest request);
    void update(UpdateColorRequest request);
    void delete(int id);
    boolean controlColorId(int id);
}
