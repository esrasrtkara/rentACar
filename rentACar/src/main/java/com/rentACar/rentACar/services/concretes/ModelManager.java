package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.Model;
import com.rentACar.rentACar.repositories.ModelRepository;
import com.rentACar.rentACar.services.abstracts.BrandService;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.dtos.requests.Model.AddModelRequest;
import com.rentACar.rentACar.services.dtos.requests.Model.UpdateModelRequest;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelListResponse;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelResponse;
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
    private BrandService brandService;

    @Override
    public List<GetModelListResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> responses = models.stream()
                .map(model -> modelMapperService.forResponse()
                        .map(model,GetModelListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetModelResponse getById(int id) {
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse response = this.modelMapperService.forResponse().map(model, GetModelResponse.class);
        return response;
    }

    @Override
    public void add(AddModelRequest request) {
        if (modelRepository.existsByName(request.getName())){
            throw new RuntimeException("Model with the same name cannot be added!");
        } else if (!brandService.controlBrandId(request.getBrand().getId())) {
            throw new RuntimeException("Brand ID not found");
        }
        Model model = this.modelMapperService.forRequest().map(request, Model.class);
        modelRepository.save(model);
    }

    @Override
    public void update(UpdateModelRequest request) {
        Model model = this.modelMapperService.forRequest().map(request, Model.class);
        modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        Model modelToDelete = modelRepository.findById(id).orElseThrow();
        modelRepository.delete(modelToDelete);
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
