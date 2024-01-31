package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.services.abstracts.RentalService;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
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
    public List<GetRentalListResponse> getAll(){
        return rentalService.getAll();
    }

    @GetMapping("{id}")
    public GetRentalResponse getById(@PathVariable int id){
        return rentalService.getById(id);
    }

    @PostMapping
    public GetRentalResponse add(@RequestBody @Valid AddRentalRequest request){
       return rentalService.add(request);
    }


    @PutMapping
    public void update(@RequestBody UpdateRentalRequest request){
        rentalService.update(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        rentalService.delete(id);
    }
}
