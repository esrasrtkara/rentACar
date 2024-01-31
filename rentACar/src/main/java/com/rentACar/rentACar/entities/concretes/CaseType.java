package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



public enum CaseType {
    SEDAN,
    HATCHBACK,
    SUV;


   public String getCaseType(){
       return name();
   }

}
