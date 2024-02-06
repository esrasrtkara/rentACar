package com.rentACar.rentACar.services.dtos.requests.User;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private int id;
    @Size(min = 3,message ="email cannot be less than three letters" )
    private String email;
    @Size(min = 1,max = 6,message ="password cannot be less than one letter and more than 6 letters" )
    private String password;
}
