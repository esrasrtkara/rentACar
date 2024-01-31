package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.TotalPriceRequest;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceListResponse;

import java.util.List;

public interface InvoiceService {
    public List<GetInvoiceListResponse> getAll();
     public void add(Long totalDay, Float price, Rental rental);
     public Float totalPrice(int rentalId);


}
