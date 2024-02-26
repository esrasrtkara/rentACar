package com.rentACar.rentACar.services.dtos.responses.Car;

import com.rentACar.rentACar.entities.concretes.Customer;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerName;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GetCarIdCommentResponse {
    private int id;
    private String text;
    private int userId;
    private int carId;


}
