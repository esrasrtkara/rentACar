package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="fuel_types")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FuelType extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fuelType")
    private List<Car> cars;

}
