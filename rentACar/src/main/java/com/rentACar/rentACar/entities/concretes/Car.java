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



    @ManyToOne
    @JoinColumn(name = "tax_rate_id")
    private TaxRate taxRate;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Comment> comments;



    @Enumerated(EnumType.STRING)
    @Column(name="case_type")
    private CaseType caseType;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name="gear_type")
    private GearType gearType;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_status")
    private CarStatus carStatus;

}
