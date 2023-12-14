package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.entities.Color;
import com.rentACar.rentACar.entities.Model;
import com.rentACar.rentACar.repositories.ColorRepository;
import com.rentACar.rentACar.services.abstracts.ColorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Data
public class ColorManager implements ColorService {
    private ColorRepository colorRepository;
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
