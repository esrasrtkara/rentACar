package com.rentACar.rentACar.entities.concretes;

public enum FuelType  {
    PETROL,
    DIESEL,
    ELECTRICITY,
    HYBRID;

   public String getFuelType(){
       return name();
   }
}
