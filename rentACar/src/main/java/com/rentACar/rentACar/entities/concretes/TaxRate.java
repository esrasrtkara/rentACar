package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tax_rates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxRate extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "rate")
    private Float rate;
}
