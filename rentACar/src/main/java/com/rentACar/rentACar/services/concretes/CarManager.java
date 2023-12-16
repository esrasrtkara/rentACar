package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.Car;
import com.rentACar.rentACar.repositories.CarRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.dtos.requests.Car.AddCarRequest;
import com.rentACar.rentACar.services.dtos.requests.Car.UpdateCarRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private ModelService modelService;
    private ColorService colorService;

    @Override
    public List<GetCarListResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetCarListResponse> responses = cars.stream().map(car -> modelMapperService.forResponse()
                .map(car,GetCarListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse response = this.modelMapperService.forResponse().map(car,GetCarResponse.class);
        return response;
    }

    @Override
    public void add(AddCarRequest request) {
        request.setPlate(request.getPlate().replaceAll("[^a-zA-Z0-9]", ""));

        if(!request.getPlate().matches("^(0[1-9]|[1-7][0-9]|8[01])(([A-Z])(\\d{4,5})|([A-Z]{2})(\\d{3,4})|([A-Z]{3})(\\d{2,3}))$"))
        {
            throw new RuntimeException("Plate number should match Turkish plate format");
        } else if (carRepository.existsByPlate(request.getPlate())) {
            throw new RuntimeException("Aynı plaka kaydedilemez");
        } else if (request.getModel().getId() < 0) {
            throw  new RuntimeException("Model id sıfırdan küçük olamaz.");
        } else if (request.getColor().getId() < 0) {
            throw  new RuntimeException("Color id sıfırdan küçük olamaz.");
        } else if (!modelService.controlModelId(request.getModel().getId())) {
            throw new RuntimeException("Model id db bulunamadı");
        }
        else if (!colorService.controlColorId(request.getColor().getId())) {
            throw new RuntimeException("Color id db bulunamadı");
        }
        else {
            Car car = this.modelMapperService.forRequest().map(request,Car.class);
            carRepository.save(car);
        }

    }

    @Override
    public void update(UpdateCarRequest request) {
        request.setPlate(request.getPlate().replaceAll("[^a-zA-Z0-9]", ""));

        if(!request.getPlate().matches("^(0[1-9]|[1-7][0-9]|8[01])(([A-Z])(\\d{4,5})|([A-Z]{2})(\\d{3,4})|([A-Z]{3})(\\d{2,3}))$"))
        {
            throw new RuntimeException("Plate number should match Turkish plate format");
        } else if (carRepository.existsByPlate(request.getPlate())) {
            throw new RuntimeException("Aynı plaka kaydedilemez");
        } else if (request.getModel().getId() < 0) {
            throw  new RuntimeException("Model id sıfırdan küçük olamaz.");
        } else if (request.getColor().getId() < 0) {
            throw  new RuntimeException("Color id sıfırdan küçük olamaz.");
        } else if (!modelService.controlModelId(request.getModel().getId())) {
            throw new RuntimeException("Model id db bulunamadı");
        }
        else if (!colorService.controlColorId(request.getColor().getId())) {
            throw new RuntimeException("Color id db bulunamadı");
        }
        else {
            Car car = this.modelMapperService.forRequest().map(request,Car.class);
            carRepository.save(car);
        }
    }

    @Override
    public void delete(int id) {
        Car carToDelete = carRepository.findById(id).orElseThrow();
        carRepository.delete(carToDelete);
    }

    @Override
    public boolean controlCarId(int id) {
        try {
            Car car = carRepository.findById(id).orElseThrow();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    @Override
    public int carKilometer(int id) {
       Car car = carRepository.findById(id).orElseThrow();
        return car.getKilometer();
    }

    @Override
    public double carDailyPrice(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        return car.getDailyPrice();
    }
}
