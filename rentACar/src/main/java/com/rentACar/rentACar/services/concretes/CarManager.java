package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.repositories.CarRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.abstracts.ColorService;
import com.rentACar.rentACar.services.abstracts.ModelService;
import com.rentACar.rentACar.services.dtos.requests.Car.AddCarRequest;
import com.rentACar.rentACar.services.dtos.requests.Car.UpdateCarRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarResponse;
import com.rentACar.rentACar.services.rules.CarBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;
    private final CarBusinessRules carBusinessRules;


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
        this.carBusinessRules.checkIfPlateFormat(request.getPlate());
        this.carBusinessRules.checkIfPlateExists(request.getPlate());
        this.carBusinessRules.checkIfModelIdZero(request.getModelId());
        this.carBusinessRules.checkIfColorIdZero(request.getColorId());
        this.carBusinessRules.checkIfModelId(request.getModelId());
        this.carBusinessRules.checkIfColorId(request.getColorId());

        Car car = this.modelMapperService.forRequest().map(request,Car.class);
        carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest request) {
        request.setPlate(request.getPlate().replaceAll("[^a-zA-Z0-9]", ""));

        this.carBusinessRules.checkIfPlateFormat(request.getPlate());
        this.carBusinessRules.checkIfPlateExists(request.getPlate());
        this.carBusinessRules.checkIfModelIdZero(request.getModelId());
        this.carBusinessRules.checkIfColorIdZero(request.getColorId());
        this.carBusinessRules.checkIfModelId(request.getModelId());
        this.carBusinessRules.checkIfColorId(request.getColorId());

            Car car = this.modelMapperService.forRequest().map(request,Car.class);
            carRepository.save(car);
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