package com.rentACar.rentACar.entities.abstracts;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "updated_date",nullable = true)
    private LocalDate updatedDate;

    @Column(name = "deleted")
    private Boolean deleted;

    @PrePersist
    private void beforedAdd(){
        createDate = LocalDate.now();
    }
    @PreUpdate
    private  void beforeUpdate(){
        updatedDate = LocalDate.now();
    }
}
