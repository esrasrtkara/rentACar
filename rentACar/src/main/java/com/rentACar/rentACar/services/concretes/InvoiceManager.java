package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Invoice;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.InvoiceRepository;
import com.rentACar.rentACar.services.abstracts.InvoiceService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.InvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceResponse;
import com.rentACar.rentACar.services.rules.InvoiceBusinessRules;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapperService modelMapperService;
   // private final InvoiceBusinessRules invoiceBusinessRules;

    @Override
    public DataResult<List<GetInvoiceListResponse>> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<GetInvoiceListResponse> responses = invoices.stream().map(invoice->modelMapperService.forResponse().map(invoice,GetInvoiceListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetInvoiceResponse> getById(int id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        GetInvoiceResponse response = modelMapperService.forResponse().map(invoice,GetInvoiceResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddInvoiceRequest request) {
       // invoiceBusinessRules.checkIfRentalId(rental.getId());
        Float price = request.getDailyPrice()* request.getTotalDay();
        Float discount = request.getDiscountRate();
        Float discountedPrice = price - (price*discount);
        Float taxAmount = discountedPrice*request.getTaxRate();
        Float totalPrice = discountedPrice + taxAmount;
        Float taxtRate = request.getTaxRate();

        Invoice invoice = new Invoice();
        invoice.setTaxtRate(taxtRate);
        invoice.setDiscountRate(discount);
        invoice.setInvoiceNo(findMaxInvoiceNumber().toString());
        invoice.setTotalPrice(totalPrice);
        invoice.setRental(request.getRental());
        invoiceRepository.save(invoice);
        return new SuccessResult(Messages.ADDED_INVOICE);
    }

    @Override
    public Result update(UpdateInvoiceRequest request) {
       // invoiceBusinessRules.checkIfRentalId(request.getRentalId());
        Invoice invoice = modelMapperService.forRequest().map(request,Invoice.class);
        invoiceRepository.save(invoice);
        return new SuccessResult(Messages.UPDATED_INVOICE);
    }

    @Override
    public Result delete(int id) {
        Invoice invoiceToDelete = invoiceRepository.findById(id).orElseThrow();
        invoiceRepository.delete(invoiceToDelete);
        return new SuccessResult(Messages.DELETED_INVOICE);
    }

    @Override
    public Float totalPrice(int rentalId) {
        Invoice invoice =invoiceRepository.findByRentalId(rentalId).orElseThrow();

        return invoice.getTotalPrice();
    }


    private Long findMaxInvoiceNumber() {
        Long maxInvoiceNumber = invoiceRepository.findMaxInvoiceNumber();
        return (maxInvoiceNumber != null) ? maxInvoiceNumber + 1 : 1L;
    }
}
