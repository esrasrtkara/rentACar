package com.rentACar.rentACar.services.dtos.responses.Car;

import com.rentACar.rentACar.entities.concretes.CaseType;
import com.rentACar.rentACar.entities.concretes.FuelType;
import com.rentACar.rentACar.entities.concretes.GearType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarListResponse {
    private int id;
    private int kilometer;
    private int year;
    private String plate;
    private double dailyPrice;
    private short minFindeksRate;
    private String imagePath;
    private int trunkVolume;
    private int capacity;
    private CaseType caseType;
    private FuelType fuelType;
    private GearType gearType;
    private String modelName;
    private String colorName;
}
