package com.rentACar.rentACar.services.dtos.requests.Invoice;

import lombok.Data;

@Data
public class InvoiceRequest {
    private String invoiceNo;
    private Float totalPrice;
    private int rentalId;
    private Float taxRate;
    private Float discountRate;
}
