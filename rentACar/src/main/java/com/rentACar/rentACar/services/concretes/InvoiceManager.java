package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Invoice;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.InvoiceRepository;
import com.rentACar.rentACar.services.abstracts.InvoiceService;
import com.rentACar.rentACar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceResponse;
import com.rentACar.rentACar.services.rules.InvoiceBusinessRules;

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
    public List<GetInvoiceListResponse> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<GetInvoiceListResponse> responses = invoices.stream().map(invoice->modelMapperService.forResponse().map(invoice,GetInvoiceListResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        GetInvoiceResponse response = modelMapperService.forResponse().map(invoice,GetInvoiceResponse.class);
        return response;
    }

    @Override
    public void add(Long totalDay, Float price, Rental rental) {
       // invoiceBusinessRules.checkIfRentalId(rental.getId());
        Invoice request = new Invoice();
        request.setRental(rental);
        request.setInvoiceNo(findMaxInvoiceNumber().toString());
        request.setDiscountRate(0.5f);
        request.setTaxRate(0.2f);
        Float discountRate = (1-request.getDiscountRate());
        Float taxRate = (1+request.getTaxRate());

        request.setTotalPrice(price*taxRate*discountRate);
        invoiceRepository.save(request);
    }

    @Override
    public void update(UpdateInvoiceRequest request) {
       // invoiceBusinessRules.checkIfRentalId(request.getRentalId());
        Invoice invoice = modelMapperService.forRequest().map(request,Invoice.class);
        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(int id) {
        Invoice invoiceToDelete = invoiceRepository.findById(id).orElseThrow();
        invoiceRepository.delete(invoiceToDelete);
    }

    @Override
    public Float totalPrice(int rentalId) {
        Invoice invoice =invoiceRepository.findByRentalId(rentalId).orElseThrow();

        return invoice.getTotalPrice();
    }


    public Long findMaxInvoiceNumber() {
        Long maxInvoiceNumber = invoiceRepository.findMaxInvoiceNumber();
        return (maxInvoiceNumber != null) ? maxInvoiceNumber + 1 : 1L;
    }
}
