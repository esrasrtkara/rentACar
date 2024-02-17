package com.rentACar.rentACar.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
