package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.core.configurations.DiscountConfiguration;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
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
public class DiscountsController {
    private final DiscountService discountService;
    private DiscountConfiguration discountConfig;

    @GetMapping
    public DataResult<List<GetDiscountListResponse>> getAll(){
        return discountService.getAll();
    }
    @GetMapping("{id}")
    public DataResult<GetDiscountByIdResponse> getById(@RequestParam int id){
        return discountService.getById(id);
    }
    @PostMapping
    public Result add(@RequestBody AddDiscountRequest request){
        return discountService.add(request);
    }
    @PutMapping
    public Result update(@RequestBody UpdateDiscountRequest request){
        return discountService.update(request);
    }
    @DeleteMapping
    public Result delete(int id){
        return discountService.delete(id);
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

    @GetMapping("userId")
    public List<GetDiscountListResponse> getDiscountUserId(){
        return discountService.getDiscountUserId();
    }
}
