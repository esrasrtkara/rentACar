package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
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
public class TaxRatesController {
    private final TaxRateService taxRateService;

    @GetMapping
    public DataResult<List<GetTaxRateListResponse>> getAll(){
        return taxRateService.getAll();
    }
    @GetMapping("{id}")
    public DataResult<GetTaxRateByIdResponse> getById(@RequestParam int id){
        return taxRateService.getById(id);
    }
    @PostMapping
    public Result add(AddTaxRateRequest request){
        return taxRateService.add(request);
    }
    @PutMapping
    public Result update(UpdateTaxRateRequest request){
        return taxRateService.update(request);
    }
    @DeleteMapping
    public Result delete(int id){
        return taxRateService.delete(id);
    }
}
