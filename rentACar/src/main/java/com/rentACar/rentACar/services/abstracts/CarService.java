package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.Comment;
import com.rentACar.rentACar.services.dtos.requests.Car.AddCarRequest;
import com.rentACar.rentACar.services.dtos.requests.Car.UpdateCarRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarIdCommentResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarResponse;

import java.util.List;

public interface CarService {
    DataResult<List<GetCarListResponse>> getAll();

    DataResult<GetCarResponse> getById(int id);

    Result add(AddCarRequest request);

    Result update(UpdateCarRequest request);

    Result delete(int id);

    boolean controlCarId(int id);

    int carKilometer(int id);

    Float carDailyPrice(int id);

    Float carTaxRate(int id);

    String carStatus(int id);

    public List<GetCarIdCommentResponse> getComment(int id);

    public void carStatusPasive(int id);
    public void carStatusActive(int id);
    public DataResult<List<GetCarListResponse>> getAllActiveCar();
    public void startKilometer(int id,int startKilometer);



}


