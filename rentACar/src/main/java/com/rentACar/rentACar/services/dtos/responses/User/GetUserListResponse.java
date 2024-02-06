package com.rentACar.rentACar.services.dtos.responses.User;

import lombok.Data;

@Data
public class GetUserListResponse {
    private String email;
    private String password;
}
