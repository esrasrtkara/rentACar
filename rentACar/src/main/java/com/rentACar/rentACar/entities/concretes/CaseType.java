package com.rentACar.rentACar.entities.concretes;

public enum CaseType {
    SEDAN,
    HATCHBACK,
    SUV;

   public String getCaseType(){
       return name();
   }

}
