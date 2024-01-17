package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.User.SignInRequest;
import com.rentACar.rentACar.services.dtos.requests.User.SignUpRequest;
import com.rentACar.rentACar.services.dtos.responses.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
