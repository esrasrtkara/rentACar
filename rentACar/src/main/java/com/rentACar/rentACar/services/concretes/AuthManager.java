package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.services.JwtService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Role;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.services.abstracts.AuthService;
import com.rentACar.rentACar.services.abstracts.CorporateCustomerService;
import com.rentACar.rentACar.services.abstracts.CustomerService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCorporateCustomer;
import com.rentACar.rentACar.services.dtos.requests.Auth.CreateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Auth.LoginRequest;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public DataResult<String> login(LoginRequest request) {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(authentication.isAuthenticated()){
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles","USER");
            return new SuccessDataResult<>(jwtService.generateToken(request.getEmail(),claims),Messages.LOGIN) ;
        }
        throw new RuntimeException(Messages.LOGIN_ERROR);
    }

    @Override
    public Result customerRegister(CreateCustomerRequest request) {
        User user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).authorities(Collections.singletonList(Role.USER)).build();
        userService.save(user);
        AddCustomerRequest customer = AddCustomerRequest.builder()
                .firstName(request.getFirstName()).lastName(request.getLastName()).userId(user.getId()).build();
        customerService.add(customer);
        return  new SuccessResult(Messages.ADDED_CUSTOMER);
    }

    @Override
    public Result corporateCustomerRegister(CreateCorporateCustomer request) {
        User user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).authorities(Collections.singletonList(Role.USER)).build();
        userService.save(user);
        AddCorporateCustomerRequest corporateCustomer = AddCorporateCustomerRequest.builder().taxNo(request.getTaxNo()).companyName(request.getCompanyName()).userId(user.getId()).build();
        corporateCustomerService.add(corporateCustomer);
        return new SuccessResult(Messages.ADDED_CORPORATE_CUSTOMER);
    }
}
