package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.repositories.CarRepository;
import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.abstracts.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository carRepository;
    private final ModelService modelService;
    private final ColorService colorService;


    public void checkIfPlateFormat(String plate){
        if(!plate.matches("^(0[1-9]|[1-7][0-9]|8[01])(([A-Z])(\\d{4,5})|([A-Z]{2})(\\d{3,4})|([A-Z]{3})(\\d{2,3}))$")) {
            throw new RuntimeException("Plate number should match Turkish plate format");
        }
    }

    public void checkIfPlateExists(String plate){
        if (carRepository.existsByPlate(plate)) {
            throw new RuntimeException("The same license plate cannot be registered");
        }
    }

    public void checkIfModelIdZero(int modelId){
        if (modelId < 0) {
            throw  new RuntimeException("Model ID cannot be less than zero.");
        }
    }
    public void checkIfColorIdZero(int colorId){
        if (colorId < 0) {
            throw  new RuntimeException("Color ID cannot be less than zero.");
        }
    }

    public void checkIfModelId(int id){
        if (!modelService.controlModelId(id)) {
            throw new RuntimeException("Model ID not found in database");
        }
    }
    public void checkIfColorId(int id){
        if (!colorService.controlColorId(id)) {
            throw new RuntimeException("Color ID not found in database");
        }
    }
}
