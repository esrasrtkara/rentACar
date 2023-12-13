package com.rentACar.rentACar.services.dtos.requests.Car;

import com.rentACar.rentACar.entities.Color;
import com.rentACar.rentACar.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class AddCarRequest {
    private int kilometer;
    private int year;
    private String plate;
    private double dailyPrice;
    private Model model;
    private Color color;
}
