package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.services.abstracts.BrandService;
import com.rentACar.rentACar.services.dtos.requests.Brand.AddBrandRequest;
import com.rentACar.rentACar.services.dtos.requests.Brand.UpdateBrandRequest;
import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandListResponse;
import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@AllArgsConstructor
public class BrandsController {

    private final BrandService brandService;

    @GetMapping
    public DataResult<List<GetBrandListResponse>> getAll(){
        return brandService.getAll();
    }

    @GetMapping("{id}")
    public DataResult<GetBrandResponse> getById(@PathVariable int id){
        return brandService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody @Valid AddBrandRequest request){
        return brandService.add(request);
    }

    @PutMapping
    public Result update(@RequestBody UpdateBrandRequest request){
        return brandService.update(request);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
       return brandService.delete(id);
    }
}