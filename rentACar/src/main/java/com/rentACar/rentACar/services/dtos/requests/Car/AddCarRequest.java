package com.rentACar.rentACar.services.dtos.requests.Car;

import com.rentACar.rentACar.entities.concretes.CaseType;
import com.rentACar.rentACar.entities.concretes.FuelType;
import com.rentACar.rentACar.entities.concretes.GearType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddCarRequest {
    @Min(value = 0,message = "Kilometer should not be less than 0")
    private int kilometer;
    @Min(value = 2005, message = "Year should not be earlier than 2005")
    @Max(value = 2024, message = "Year should not be later than 2024")
    private int year;
    //@Pattern(regexp = "^(0[1-9]|[1-7][0-9]|8[01])(([A-Z])(\\d{4,5})|([A-Z]{2})(\\d{3,4})|([A-Z]{3})(\\d{2,3}))$", message = "Plate number should match Turkish plate format")
    private String plate;
    @Min(value = 0,message = "Daily price should not be less than 0")
    private double dailyPrice;
    private MultipartFile file;
    private short minFindeksRate;
    private int trunkVolume;
    @Min(value = 2, message = "Capacity should not be earlier than 2")
    private int capacity;
    private CaseType caseType;
    private FuelType fuelType;
    private GearType gearType;
    private  int modelId;
    private int colorId;
}
