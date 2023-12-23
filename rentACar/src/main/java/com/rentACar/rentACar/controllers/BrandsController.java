package com.rentACar.rentACar.controllers;

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
    public List<GetBrandListResponse> getAll(){
        return brandService.getAll();
    }

    @GetMapping("{id}")
    public GetBrandResponse getById(@PathVariable int id){
        return brandService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody @Valid AddBrandRequest request){
        brandService.add(request);
    }

    @PutMapping
    public void update(@RequestBody UpdateBrandRequest request){
        brandService.update(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}