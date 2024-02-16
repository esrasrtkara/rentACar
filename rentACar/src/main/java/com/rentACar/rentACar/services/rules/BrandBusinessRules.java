package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.entities.concretes.Brand;
import com.rentACar.rentACar.entities.concretes.Model;
import com.rentACar.rentACar.repositories.BrandRepository;
import com.rentACar.rentACar.services.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    private final BrandRepository brandRepository;

    public void checkIfBrandNameExist(String name){
        if(this.brandRepository.existsByName(name)){
            throw new RuntimeException(Messages.SAME_BRAND_EXISTS);
        }
    }

    public void modelDeleted(Brand brand){
        brand.setDeleted(true);
        List<Model> models = brand.getModels();

        brand.getModels().forEach(model -> {
            model.setDeleted(brand.getDeleted());
            model.getCars().forEach(car -> car.setDeleted(model.getDeleted()));
        });
    }


}
