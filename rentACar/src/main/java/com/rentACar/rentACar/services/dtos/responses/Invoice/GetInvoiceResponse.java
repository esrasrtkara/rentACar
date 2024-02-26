package com.rentACar.rentACar.services.dtos.responses.Invoice;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetInvoiceResponse {
    private int id;
    private String invoiceNo;
    private Float totalPrice;
    private Float discountRate;
    private Float taxRate;
    private int rentalId;
    private LocalDate createDate;
}
