package com.rentACar.rentACar.services.dtos.requests.User;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private int id;
    private String name;
    private String surname;
    private String gsm;
    private String email;
}
