package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCorporateCustomer;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Auth.LoginRequest;

public interface AuthService {
    public String login(LoginRequest request);
    public void customerRegister(CreateCustomerRequest request);
    public void corporateCustomerRegister(CreateCorporateCustomer request);
}
