package com.rentACar.rentACar.entities.concretes;

public enum FuelType  {
    GASOLINE,
    DIESEL,
    ELECTRICITY;

   public String getFuelType(){
       return name();
   }
}
