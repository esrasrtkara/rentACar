package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.Model;
import com.rentACar.rentACar.repositories.ModelRepository;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    @Override
    public List<GetModelListResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> responses = models.stream()
                .map(model -> modelMapperService.forResponse()
                        .map(model,GetModelListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public boolean controlModelId(int id) {
       try {
           Model model = modelRepository.findById(id).orElseThrow();
           return true;
       }catch (NoSuchElementException e){
           return false;
       }
    }
}
