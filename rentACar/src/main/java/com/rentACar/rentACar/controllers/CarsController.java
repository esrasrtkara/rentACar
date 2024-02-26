package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Comment;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.dtos.requests.Car.AddCarRequest;
import com.rentACar.rentACar.services.dtos.requests.Car.UpdateCarRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarIdCommentResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
@CrossOrigin
public class CarsController {
    private final CarService carService;

    @GetMapping
    public DataResult<List<GetCarListResponse>> getAll(){
        return carService.getAll();
    }

    @GetMapping("active")
    public DataResult<List<GetCarListResponse>> getAllActiveCar(){
        return carService.getAllActiveCar();
    }
    @GetMapping("/{id}")
    public DataResult<GetCarResponse> getById(@PathVariable int id){
        return carService.getById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result add(@ModelAttribute @Valid AddCarRequest request){
        return carService.add(request);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Result update(@ModelAttribute @Valid UpdateCarRequest request){
        return carService.update(request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result delete(@PathVariable int id){
        return carService.delete(id);
    }

    @GetMapping("/{carId}/comments")
    public List<GetCarIdCommentResponse> getCommentsForCar(@PathVariable int carId) {
        return carService.getComment(carId);
    }
    @GetMapping("discountCar")
    public List<GetCarListResponse> getDiscountedCars(){
      return   carService.getDiscountedCars();
    }
}
