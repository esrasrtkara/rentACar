package com.rentACar.rentACar.services.dtos.requests.Invoice;

import com.rentACar.rentACar.entities.concretes.Rental;
import lombok.Data;

@Data
public class AddInvoiceRequest {

    private Long totalDay;
    private Float dailyPrice;
    private Float discountRate;
    private Float taxRate;
    private Rental rental;
}
