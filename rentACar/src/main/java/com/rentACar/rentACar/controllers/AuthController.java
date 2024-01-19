package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.AuthService;
import com.rentACar.rentACar.services.dtos.requests.auth.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auths")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
}
