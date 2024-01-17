package com.rentACar.rentACar.services.dtos.requests.User;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
