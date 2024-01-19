package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "gear_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GearType extends BaseEntity {
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "gearType")
    private List<Car> cars;

}
