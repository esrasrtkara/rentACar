package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.repositories.ModelRepository;
import com.rentACar.rentACar.services.abstracts.BrandService;
import com.rentACar.rentACar.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository modelRepository;
    private final BrandService brandService;


    public void checkIfModelNameExists(String name){
        if (modelRepository.existsByName(name)){
            throw new RuntimeException(Messages.SAME_MODEL_EXISTS);
        }
    }

    public void checkIfBrandId(int id){
        if(!brandService.controlBrandId(id)){
            throw new RuntimeException(Messages.BRAND_ID_NOT_FOUND);
        }
    }
}
