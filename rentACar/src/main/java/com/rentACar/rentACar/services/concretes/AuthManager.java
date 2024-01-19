package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.services.JwtService;
import com.rentACar.rentACar.services.abstracts.AuthService;
import com.rentACar.rentACar.services.dtos.requests.auth.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public String login(LoginRequest request) {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(authentication.isAuthenticated()){
            Map<String, Object> claims = new HashMap<>();
            return jwtService.generateToken(request.getEmail(),claims);
        }
        throw new RuntimeException("Bilgiler Hatalı");
    }
}
