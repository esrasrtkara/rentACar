package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.TotalPriceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    public List<GetInvoiceListResponse> getAll();
    public GetInvoiceResponse getById(int id);
    public void add(AddInvoiceRequest request);
    public void update(UpdateInvoiceRequest request);
    public void delete(int id);

    //deneme için
    public Float totalPrice(int rentalId);



}
