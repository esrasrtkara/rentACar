package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCorporateCustomer;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Auth.LoginRequest;
import com.rentACar.rentACar.services.dtos.responses.Auth.AuthResponse;

public interface AuthService {
    public AuthResponse login(LoginRequest request);
    public Result customerRegister(CreateCustomerRequest request);
    public Result corporateCustomerRegister(CreateCorporateCustomer request);
}
