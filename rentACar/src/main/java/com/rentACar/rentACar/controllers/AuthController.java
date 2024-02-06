package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.AuthService;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCorporateCustomer;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Auth.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    @PostMapping("login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
    @PostMapping("customer-register")
    public void customerRegister(@RequestBody CreateCustomerRequest request){
        authService.customerRegister(request);
    }
    @PostMapping("corporate-register")
    public void corporateCustomerRegister(@RequestBody CreateCorporateCustomer request){
        authService.corporateCustomerRegister(request);
    }
}
