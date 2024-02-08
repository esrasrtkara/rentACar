package com.rentACar.rentACar.entities.concretes;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum GearType  {
    AUTOMATIC,
    MANUAL;

    @Contract(pure = true)
    public @NotNull String getGearType(){
        return name();
    }

}
