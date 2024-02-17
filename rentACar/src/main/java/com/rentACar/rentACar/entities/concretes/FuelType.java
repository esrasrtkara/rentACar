package com.rentACar.rentACar.entities.concretes;

public enum FuelType  {
    PETROL,
    DIESEL,
    ELECTRICITY,
    HYBRİD;

   public String getFuelType(){
       return name();
   }
}
