package com.rentACar.rentACar.entities.concretes;

import com.rentACar.rentACar.entities.abstracts.BaseEntity;
import  jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name="invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends BaseEntity {
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name = "total_price")
    private Float totalPrice;
    @Column(name = "discount_rate")
    private Float discountRate;
    @Column(name = "taxt_rate")
    private Float taxtRate;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}
