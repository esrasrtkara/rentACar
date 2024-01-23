package com.rentACar.rentACar.services.dtos.requests.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserRequest {
    private String email;
    private String password;
}
