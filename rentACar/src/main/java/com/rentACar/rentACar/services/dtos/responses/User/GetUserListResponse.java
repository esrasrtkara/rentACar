package com.rentACar.rentACar.services.dtos.responses.User;

import com.rentACar.rentACar.entities.concretes.Role;
import lombok.Data;

import java.util.List;

@Data
public class GetUserListResponse {
    private String email;
    private String password;
    private List<Role> roles;
}
