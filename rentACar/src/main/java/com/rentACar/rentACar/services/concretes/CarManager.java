package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.entities.Car;
import com.rentACar.rentACar.repositories.CarRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.dtos.requests.Car.AddCarRequest;
import com.rentACar.rentACar.services.dtos.requests.Car.UpdateCarRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private CarRepository carRepository;

    @Override
    public List<GetCarListResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetCarListResponse> responses = new ArrayList<>();

        for (Car car : cars){
            GetCarListResponse response = new GetCarListResponse();
            response.setYear(car.getYear());
            response.setKilometer(car.getKilometer());
            response.setPlate(car.getPlate());
            response.setDailyPrice(car.getDailyPrice());
            responses.add(response);
        }
        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();

        GetCarResponse response = new GetCarResponse();
        response.setId(car.getId());
        response.setYear(car.getYear());
        response.setPlate(car.getPlate());
        response.setKilometer(car.getKilometer());
        response.setDailyPrice(car.getDailyPrice());
        return response;
    }

    @Override
    public void add(AddCarRequest request) {

    }

    @Override
    public void update(UpdateCarRequest request) {

    }

    @Override
    public void delete(int id) {

    }
}
