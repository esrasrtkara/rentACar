package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.Color;
import com.rentACar.rentACar.repositories.ColorRepository;
import com.rentACar.rentACar.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ColorBusinessRules {
    private final ColorRepository colorRepository;

    public void checkIfColorNameExist(String name){
        if(this.colorRepository.existsByName(name)){
            throw new IllegalArgumentException(Messages.SAME_COLOR_EXISTS);
        }
    }
    public void checkIfColorCodeExist(String code){
        if(this.colorRepository.existsByCode(code)){
            throw new IllegalArgumentException(Messages.SAME_COLOR_CODE_EXISTS);
        }
    }

    public void deletedCar(Color color){
        color.setDeleted(true);
        List<Car> cars = color.getCars();
        color.getCars().forEach(car -> {
            car.setDeleted(color.getDeleted());
        });
    }

}
