package com.rentACar.rentACar.services.dtos.requests.Invoice;

import lombok.Data;

@Data
public class UpdateInvoiceRequest {
    private int id;
    private String invoiceNo;
    private Float totalPrice;
    private Float discountRate;
    private Float taxRate;
    private int rentalId;

}
