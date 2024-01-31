package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@AllArgsConstructor
@CrossOrigin
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping()
    public Float totalPrice( @RequestParam int rentalId){
        return invoiceService.totalPrice(rentalId);
    }
}
