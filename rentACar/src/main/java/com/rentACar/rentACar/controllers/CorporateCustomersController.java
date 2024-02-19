package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.CorporateCustomer;
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
@CrossOrigin
public class CorporateCustomersController {
    private final CorporateCustomerService corporateCustomerService;

    @GetMapping
    public DataResult<List<GetCorporateCustomerListResponse>> getAll(){
        return corporateCustomerService.getAll();
    }
    @GetMapping("{id}")
    public DataResult<GetCorporateCustomerResponse> getById(@PathVariable int id){
        return corporateCustomerService.getById(id);
    }
    @PostMapping
    public Result add(@RequestBody @Valid AddCorporateCustomerRequest request){
        return corporateCustomerService.add(request);
    }
    @PutMapping
    public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest request){
        return corporateCustomerService.update(request);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return corporateCustomerService.delete(id);
    }

    @GetMapping("/user")
    public CorporateCustomer getCorporateCustomer(){
        return corporateCustomerService.getCorporateCustomer();
    }
}
