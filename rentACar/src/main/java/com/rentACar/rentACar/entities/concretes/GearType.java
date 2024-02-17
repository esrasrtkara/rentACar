package com.rentACar.rentACar.entities.concretes;
<<<<<<< HEAD


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

=======
>>>>>>> main
public enum GearType  {
    AUTOMATIC,
    MANUAL;

    @Contract(pure = true)
    public @NotNull String getGearType(){
        return name();
    }

}
