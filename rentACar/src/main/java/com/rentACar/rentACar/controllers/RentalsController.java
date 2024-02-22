package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.services.abstracts.RentalService;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.CarFilterRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetCarFilterResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalListResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalResponse;
import com.stripe.model.Charge;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@AllArgsConstructor
@CrossOrigin
public class RentalsController {
    private final RentalService rentalService;


    @GetMapping
    public DataResult<List<GetRentalListResponse>> getAll(){
        return rentalService.getAll();
    }

    @GetMapping("{id}")
    public DataResult<GetRentalResponse> getById(@PathVariable int id){
        return rentalService.getById(id);
    }

    @GetMapping("userId")
    public  List<GetRentalListResponse>  getRentalUserId(){
        return rentalService.getRentalUserId();
    }

    @PostMapping("/filter")
    public GetCarFilterResponse carFilter(@RequestBody CarFilterRequest request){
        return rentalService.carFilter(request);
    }

    @PostMapping
    public DataResult<GetRentalResponse> add(@RequestBody @Valid AddRentalRequest request){
       return rentalService.add(request);
    }


    @PutMapping
    public Result update(@RequestBody UpdateRentalRequest request){
        return rentalService.update(request);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return rentalService.delete(id);
    }
}
