package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.entities.Brand;
import com.rentACar.rentACar.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/brands")
@AllArgsConstructor
public class BrandsController {
    private BrandRepository brandRepository;

    @PostMapping
    public void add(@RequestBody Brand brand){
        brandRepository.save(brand);
    }
}
