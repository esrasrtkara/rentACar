package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.CorporateCustomerService;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.UpdateCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corporates")
@AllArgsConstructor
public class CorporateCustomerController {
    private final CorporateCustomerService corporateCustomerService;

    @GetMapping
    public List<GetCorporateCustomerListResponse> getAll(){
        return corporateCustomerService.getAll();
    }
    @GetMapping("{id}")
    public GetCorporateCustomerResponse getById(@PathVariable int id){
        return corporateCustomerService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody @Valid AddCorporateCustomerRequest request){
        corporateCustomerService.add(request);
    }
    @PutMapping
    public void update(@RequestBody @Valid UpdateCorporateCustomerRequest request){
        corporateCustomerService.update(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        corporateCustomerService.delete(id);
    }
}
