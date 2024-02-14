package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.configurations.DiscountConfiguration;
import com.rentACar.rentACar.services.abstracts.DiscountService;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.UpdateDiscountRequest;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@AllArgsConstructor
public class DiscountController {
    private final DiscountService discountService;
    private DiscountConfiguration discountConfig;

    @GetMapping
    public List<GetDiscountListResponse> getAll(){
        return discountService.getAll();
    }
    @GetMapping("{id}")
    public GetDiscountByIdResponse getById(@RequestParam int id){
        return discountService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddDiscountRequest request){
        discountService.add(request);
    }
    @PutMapping
    public void update(@RequestBody UpdateDiscountRequest request){
        discountService.update(request);
    }
    @DeleteMapping
    public void delete(int id){
        discountService.delete(id);
    }

    @PutMapping("/config")
    public void updateConfig(@RequestBody DiscountConfiguration updatedConfig) {
        discountConfig.setName(updatedConfig.getName());
        discountConfig.setRate(updatedConfig.getRate());
        discountConfig.setRentalCount(updatedConfig.getRentalCount());
    }
    @GetMapping("/getconfig")
    public DiscountConfiguration getConfig() {
        return discountConfig;
    }
}
