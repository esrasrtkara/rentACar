package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "discounts")
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "code_status")
    private String codeStatus;
    @Column(name = "rate")
    private Float rate;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_Date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
