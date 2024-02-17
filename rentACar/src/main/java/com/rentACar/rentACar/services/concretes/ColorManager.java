package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Color;
import com.rentACar.rentACar.repositories.ColorRepository;
import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Color.AddColorRequest;
import com.rentACar.rentACar.services.dtos.requests.Color.UpdateColorRequest;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorListResponse;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorResponse;
import com.rentACar.rentACar.services.rules.ColorBusinessRules;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class ColorManager implements ColorService {
    private final ColorBusinessRules colorBusinessRules;
    private final ColorRepository colorRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetColorListResponse>> getAll() {
        List<Color> colors = this.colorRepository.findAll();
        List<GetColorListResponse> responses = colors.stream().map(color -> modelMapperService.forResponse().
                map(color, GetColorListResponse.class)).collect(Collectors.toList());
        return  new SuccessDataResult<List<GetColorListResponse>>(responses);
    }

    @Override
    public DataResult<GetColorResponse> getById(int id) {
        Color color = colorRepository.findById(id).orElseThrow();

        GetColorResponse response = this.modelMapperService.forResponse().map(color , GetColorResponse.class);
        return new SuccessDataResult<GetColorResponse>(response);
    }

    @Override
    public Result add(AddColorRequest request) {
        colorBusinessRules.checkIfColorNameExist(request.getName());
        colorBusinessRules.checkIfColorCodeExist(request.getCode());
        Color color = modelMapperService.forRequest().map(request, Color.class);
        color.setName(request.getName().toUpperCase());
        colorRepository.save(color);
        return new SuccessResult(Messages.ADDED_COLOR);
    }

    @Override
    public Result update(UpdateColorRequest request) {
        colorBusinessRules.checkIfColorNameExist(request.getName());
        colorBusinessRules.checkIfColorCodeExist(request.getCode());
        Color color = modelMapperService.forRequest().map(request, Color.class);
        color.setName(request.getName().toUpperCase());
        colorRepository.save(color);
        return new SuccessResult(Messages.UPDATED_COLOR);
    }

    @Override
    public Result delete(int id) {
        Color colorToDelete = colorRepository.findById(id).orElseThrow();
        colorRepository.delete(colorToDelete);
        return new SuccessResult(Messages.DELETED_COLOR);
    }

    @Override
    public boolean controlColorId(int id) {
        try {
            Color color = colorRepository.findById(id).orElseThrow();
            return  true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }
}