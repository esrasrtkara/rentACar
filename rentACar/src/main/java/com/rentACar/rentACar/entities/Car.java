package com.rentACar.rentACar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "kilometer")
    private int kilometer;

    @Column(name = "year")
    private int year;

    @Column(name = "plate")
    private String plate;

    @Column(name = "daily_price")
    private double dailyPrice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rental> rentals;
}
