package com.rentACar.rentACar.entities.concretes;



import org.jetbrains.annotations.NotNull;

public enum GearType  {
    AUTOMATIC,
    MANUAL;
    public @NotNull String getGearType(){
        return name();
    }

}