package com.rentACar.rentACar.services.dtos.responses.User;

import lombok.Data;

@Data
public class GetUserResponse {
    private int id;
    private String name;
    private String surname;
    private String gsm;
    private String email;
}
