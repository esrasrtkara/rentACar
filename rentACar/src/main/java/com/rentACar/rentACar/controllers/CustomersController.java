package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Customer;
import com.rentACar.rentACar.services.abstracts.CustomerService;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
@CrossOrigin
public class CustomersController {

    private final CustomerService customerService;

    @GetMapping
    public DataResult<List<GetCustomerListResponse>> getAll(){
        return customerService.getAll();
    }

    @GetMapping("{id}")
    public DataResult<GetCustomerResponse> getById(@PathVariable int id){
        return customerService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody @Valid AddCustomerRequest request){
        return customerService.add(request);
    }

    @PutMapping
    public Result update(@RequestBody UpdateCustomerRequest request){
        return customerService.update(request);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return customerService.delete(id);
    }

    @GetMapping("/user")
    public Customer getCustomer(){
        return customerService.getCustomer();
    }


}
