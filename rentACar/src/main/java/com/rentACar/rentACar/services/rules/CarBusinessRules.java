package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.CarRepository;
import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository carRepository;
    private final ModelService modelService;
    private final ColorService colorService;


    public void checkIfPlateFormat(String plate){
        if(!plate.matches("^(0[1-9]|[1-7][0-9]|8[01])(([A-Z])(\\d{4,5})|([A-Z]{2})(\\d{3,4})|([A-Z]{3})(\\d{2,3}))$")) {
            throw new RuntimeException(Messages.CHECK_IF_PLATE_FORMAT);
        }
    }

    public void checkIfPlateExists(String plate){
        if (carRepository.existsByPlate(plate)) {
            throw new RuntimeException(Messages.SAME_CAR_PLATE_EXISTS);
        }
    }

    public void checkIfModelIdZero(int modelId){
        if (modelId < 0) {
            throw  new RuntimeException(Messages.CHECK_IF_MODEL_ID_ZERO);
        }
    }
    public void checkIfColorIdZero(int colorId){
        if (colorId < 0) {
            throw  new RuntimeException(Messages.CHECK_IF_COLOR_ID_ZERO);
        }
    }

    public void checkIfModelId(int id){
        if (!modelService.controlModelId(id)) {
            throw new RuntimeException(Messages.CHECK_IF_MODEL_ID);
        }
    }
    public void checkIfColorId(int id){
        if (!colorService.controlColorId(id)) {
            throw new RuntimeException(Messages.CHECK_IF_COLOR_ID);
        }
    }

    public void deletedRental(Car car){
        car.setDeleted(true);
        List<Rental> rentals = car.getRentals();
        car.getRentals().forEach(rental -> {
            rental.setDeleted(car.getDeleted());
            rental.getInvoices().forEach(invoice -> invoice.setDeleted(rental.getDeleted()));
        });

    }
}
