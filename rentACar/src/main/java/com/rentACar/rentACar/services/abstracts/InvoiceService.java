package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.TotalPriceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceResponse;

import java.util.List;

public interface InvoiceService {
    public DataResult<List<GetInvoiceListResponse>> getAll();
    public DataResult<GetInvoiceResponse> getById(int id);
    public Result add(AddInvoiceRequest request);
    public Result update(UpdateInvoiceRequest request);
    public Result delete(int id);

    //deneme için
    public Float totalPrice(int rentalId);

    public GetInvoiceResponse getIncoiceRentalId(int rentalId);



}
