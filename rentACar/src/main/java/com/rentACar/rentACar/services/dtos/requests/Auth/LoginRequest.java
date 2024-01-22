package com.rentACar.rentACar.services.dtos.requests.Auth;

import com.rentACar.rentACar.entities.concretes.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
   private String email;
   private String password;
}
