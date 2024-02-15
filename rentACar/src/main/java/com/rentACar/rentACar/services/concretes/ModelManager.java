package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Model;
import com.rentACar.rentACar.repositories.ModelRepository;
import com.rentACar.rentACar.services.abstracts.BrandService;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Model.AddModelRequest;
import com.rentACar.rentACar.services.dtos.requests.Model.UpdateModelRequest;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelListResponse;
import com.rentACar.rentACar.services.dtos.responses.Model.GetModelResponse;
import com.rentACar.rentACar.services.rules.ModelBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapperService modelMapperService;
    private final ModelBusinessRules modelBusinessRules;


    @Override
    public DataResult<List<GetModelListResponse>> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> responses = models.stream()
                .map(model -> modelMapperService.forResponse()
                        .map(model,GetModelListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetModelListResponse>>(responses);
    }

    @Override
    public DataResult<GetModelResponse> getById(int id) {
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse response = this.modelMapperService.forResponse().map(model, GetModelResponse.class);
        return new SuccessDataResult<GetModelResponse>(response);
    }

    @Override
    public Result add(AddModelRequest request) {
        this.modelBusinessRules.checkIfModelNameExists(request.getName());
        this.modelBusinessRules.checkIfBrandId(request.getBrandId());

        Model model = this.modelMapperService.forRequest().map(request, Model.class);
        model.setName(request.getName().toUpperCase());
        modelRepository.save(model);
        return new SuccessResult(Messages.ADDED_MODEL);
    }

    @Override
    public Result update(UpdateModelRequest request) {
        this.modelBusinessRules.checkIfModelNameExists(request.getName());
        this.modelBusinessRules.checkIfBrandId(request.getBrandId());

        Model model = this.modelMapperService.forRequest().map(request, Model.class);
        model.setName(request.getName().toUpperCase());
        modelRepository.save(model);
        return new SuccessResult(Messages.UPDATED_MODEL);
    }

    @Override
    public Result delete(int id) {
        Model modelToDelete = modelRepository.findById(id).orElseThrow();
        modelRepository.delete(modelToDelete);
        return new SuccessResult(Messages.DELETED_MODEL);
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