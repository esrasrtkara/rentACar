package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.InvoiceService;
import com.rentACar.rentACar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentACar.rentACar.services.dtos.responses.Invoice.GetInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
@CrossOrigin
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("getall")
    List<GetInvoiceListResponse> getAll(){
        return invoiceService.getAll();
    }
    @GetMapping("{id}")
    GetInvoiceResponse getById(@PathVariable int id){
        return invoiceService.getById(id);
    }
    @PostMapping
    void add(@RequestBody AddInvoiceRequest request){
        invoiceService.add(request);
    }
    @PutMapping
    void update(@RequestBody UpdateInvoiceRequest request){
        invoiceService.update(request);
    }
    @DeleteMapping("{id}")
    void delete(@PathVariable int id){
        invoiceService.delete(id);
    }
    @GetMapping()
    public Float totalPrice( @RequestParam int rentalId){
        return invoiceService.totalPrice(rentalId);
    }
}
