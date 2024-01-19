package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.auth.LoginRequest;

public interface AuthService {
    public String login(LoginRequest request);
}
