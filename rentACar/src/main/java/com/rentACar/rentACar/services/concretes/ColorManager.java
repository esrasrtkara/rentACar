package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.Color;
import com.rentACar.rentACar.entities.Model;
import com.rentACar.rentACar.repositories.ColorRepository;
import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.dtos.requests.Color.AddColorRequest;
import com.rentACar.rentACar.services.dtos.requests.Color.UpdateColorRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorListResponse;
import com.rentACar.rentACar.services.dtos.responses.Color.GetColorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class ColorManager implements ColorService {
    private ColorRepository colorRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetColorListResponse> getAll() {
        List<Color> colors = this.colorRepository.findAll();
        List<GetColorListResponse> responses = colors.stream().map(color -> modelMapperService.forResponse().
                map(color, GetColorListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetColorResponse getById(int id) {
        Color color = colorRepository.findById(id).orElseThrow();

        GetColorResponse response = this.modelMapperService.forResponse().map(color , GetColorResponse.class);
        return response;
    }

    @Override
    public void add(AddColorRequest request) {
        if (colorRepository.existsByName(request.getName())){
            throw new RuntimeException("Aynı isimli renk eklenemez!");
        }
        Color color = modelMapperService.forRequest().map(request, Color.class);
        colorRepository.save(color);
    }

    @Override
    public void update(UpdateColorRequest request) {
        Color color = modelMapperService.forRequest().map(request, Color.class);
        colorRepository.save(color);
    }

    @Override
    public void delete(int id) {
        Color colorToDelete = colorRepository.findById(id).orElseThrow();
        colorRepository.delete(colorToDelete);
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
