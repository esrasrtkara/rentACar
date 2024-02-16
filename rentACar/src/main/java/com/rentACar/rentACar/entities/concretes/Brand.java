package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {


    @Column(name = "name")
    private String name;
    @Column(name = "logo_path")
    private String logoPath;
    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;


}
