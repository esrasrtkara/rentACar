package com.rentACar.rentACar.services.dtos.responses.Auth;

import lombok.Data;

@Data
public class AuthResponse {
   private String message;
   private   int userId;
   private String accessToken;
   private String refreshToken;
}
