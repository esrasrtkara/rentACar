package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.services.JwtService;
import com.rentACar.rentACar.entities.concretes.Customer;
import com.rentACar.rentACar.entities.concretes.Role;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.services.abstracts.AuthService;
import com.rentACar.rentACar.services.abstracts.CorporateCustomerService;
import com.rentACar.rentACar.services.abstracts.CustomerService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCorporateCustomer;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Auth.LoginRequest;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Model.AddModelIdRequest;
import com.rentACar.rentACar.services.dtos.requests.User.AddUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.FileNameMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomerService customerService;
    private final CorporateCustomerService corporateCustomerService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String login(LoginRequest request) {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(authentication.isAuthenticated()){
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles","USER");
            return jwtService.generateToken(request.getEmail(),claims);
        }
        throw new RuntimeException("Bilgiler Hatalı");
    }

    @Override
    public void customerRegister(CreateCustomerRequest request) {
        User user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).authorities(Collections.singletonList(Role.USER)).build();
        userService.save(user);
        AddCustomerRequest customer = AddCustomerRequest.builder()
                .firstName(request.getFirstName()).lastName(request.getLastName()).userId(user.getId()).build();
        customerService.add(customer);
    }

    @Override
    public void corporateCustomerRegister(CreateCorporateCustomer request) {
        User user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).authorities(Collections.singletonList(Role.USER)).build();
        userService.save(user);
        AddCorporateCustomerRequest corporateCustomer = AddCorporateCustomerRequest.builder().taxNo(request.getTaxNo()).companyName(request.getCompanyName()).userId(user.getId()).build();
        corporateCustomerService.add(corporateCustomer);
    }
}
