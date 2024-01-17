package com.rentACar.rentACar.services.dtos.requests.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SignUpRequest {
    private String email;
    private String password;
}
