package com.rentACar.rentACar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.List;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {

    @Column(name = "kilometer")
    private int kilometer;
    @Column(name = "year")
    private int year;
    @Column(name = "plate")
    private String plate;
    @Column(name = "daily_price")
    private Float dailyPrice;
    @Column(name = "min_findeks_rate")
    private short minFindeksRate;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "trunk_volume")
    private int trunkVolume;
    @Column(name = "capacity")
    private int capacity;



    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;


    @ManyToOne
    @JoinColumn(name="caseType_id")
    private CaseType caseType;

    @ManyToOne
    @JoinColumn(name="fuelType_id")
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name="gearType_id")
    private GearType gearType;

}
