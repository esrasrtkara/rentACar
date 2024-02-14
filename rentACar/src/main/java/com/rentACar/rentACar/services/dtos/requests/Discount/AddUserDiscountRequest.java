package com.rentACar.rentACar.services.dtos.requests.Discount;

import com.rentACar.rentACar.entities.concretes.Rental;
import lombok.Data;

@Data
public class AddUserDiscountRequest {
    private Rental rental;
    private Float discount;
}
