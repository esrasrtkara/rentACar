package com.rentACar.rentACar.services.dtos.responses.Rental;

import lombok.Data;

@Data
public class GetCarFilterResponse {
    private String carStatus;
    private Float totalPrice;
    private Float discount;
}
