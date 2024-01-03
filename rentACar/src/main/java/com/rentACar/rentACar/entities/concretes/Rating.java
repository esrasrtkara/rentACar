package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating extends BaseEntity {
    @Column(name = "point")
    private float point;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
