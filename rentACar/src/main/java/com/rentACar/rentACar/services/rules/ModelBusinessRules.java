package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.repositories.ModelRepository;
import com.rentACar.rentACar.services.abstracts.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository modelRepository;
    private final BrandService brandService;


    public void checkIfModelNameExists(String name){
        if (modelRepository.existsByName(name)){
            throw new RuntimeException("Model with the same name cannot be added!");
        }
    }

    public void checkIfBrandId(int id){
        if(!brandService.controlBrandId(id)){
            throw new RuntimeException("Brand ID not found" );
        }
    }


}
