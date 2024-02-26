package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
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

    @GetMapping()
    DataResult<List<GetInvoiceListResponse>> getAll(){
        return invoiceService.getAll();
    }
    @GetMapping("{id}")
    DataResult<GetInvoiceResponse> getById(@PathVariable int id){
        return invoiceService.getById(id);
    }
    @PostMapping
    Result add(@RequestBody AddInvoiceRequest request){
        return invoiceService.add(request);
    }
    @PutMapping
    Result update(@RequestBody UpdateInvoiceRequest request){
        return invoiceService.update(request);
    }
    @DeleteMapping("{id}")
    Result delete(@PathVariable int id){
        return invoiceService.delete(id);
    }
   /* @GetMapping("{total}")
    public Float totalPrice( @RequestParam int rentalId){
        return invoiceService.totalPrice(rentalId);
    }*/
    @GetMapping("rental/{rentalId}")
    public GetInvoiceResponse getIncoiceRentalId(@PathVariable int rentalId){
        return invoiceService.getIncoiceRentalId(rentalId);
    }

}
