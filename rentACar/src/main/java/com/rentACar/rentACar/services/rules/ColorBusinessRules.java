package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.repositories.ColorRepository;
import com.rentACar.rentACar.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ColorBusinessRules {
    private final ColorRepository colorRepository;

    public void checkIfColorNameExist(String name){
        if(this.colorRepository.existsByName(name)){
            throw new RuntimeException(Messages.SAME_COLOR_EXISTS);
        }
    }
    public void checkIfColorCodeExist(String code){
        if(this.colorRepository.existsByCode(code)){
            throw new RuntimeException(Messages.SAME_COLOR_CODE_EXISTS);
        }
    }

}
