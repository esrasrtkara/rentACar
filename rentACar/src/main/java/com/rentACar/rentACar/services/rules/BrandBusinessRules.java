package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private final BrandRepository brandRepository;

    public void checkIfBrandNameExist(String name){
        if(this.brandRepository.existsByName(name)){
            throw new RuntimeException("The same name brand cannot be added!");
        }
    }


}
