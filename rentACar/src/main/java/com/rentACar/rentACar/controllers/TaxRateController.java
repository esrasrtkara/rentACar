package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.TaxRateService;
import com.rentACar.rentACar.services.dtos.requests.TaxRate.AddTaxRateRequest;
import com.rentACar.rentACar.services.dtos.requests.TaxRate.UpdateTaxRateRequest;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.TaxRate.GetTaxRateListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax_rates")
@AllArgsConstructor
public class TaxRateController {
    private final TaxRateService taxRateService;

    @GetMapping
    public List<GetTaxRateListResponse> getAll(){
        return taxRateService.getAll();
    }
    @GetMapping("{id}")
    public GetTaxRateByIdResponse getById(@RequestParam int id){
        return taxRateService.getById(id);
    }
    @PostMapping
    public void add(AddTaxRateRequest request){
        taxRateService.add(request);
    }
    @PutMapping
    public void update(UpdateTaxRateRequest request){
        taxRateService.update(request);
    }
    @DeleteMapping
    public void delete(int id){
        taxRateService.delete(id);
    }
}
