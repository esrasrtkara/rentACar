package com.rentACar.rentACar.services.dtos.requests.Color;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class AddColorRequest {
    @Size(min = 2 , message = "Renk adı 2 karakterden küçük olamaz!")
    private String name;
}
