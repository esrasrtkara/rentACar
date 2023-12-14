package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.dtos.requests.Car.AddCarRequest;
import com.rentACar.rentACar.services.dtos.requests.Car.UpdateCarRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@AllArgsConstructor
public class CarsController {
    private final CarService carService;

    @GetMapping
    public List<GetCarListResponse> getAll(){
        return carService.getAll();
    }

    @GetMapping("{id}")
    public GetCarResponse getById(@PathVariable int id){
        return carService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid AddCarRequest request){
        carService.add(request);
    }
    @PutMapping
    public void update(@RequestBody @Valid UpdateCarRequest request){
        carService.update(request);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        carService.delete(id);
    }

}
