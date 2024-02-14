package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.repositories.DiscountRepository;
import com.rentACar.rentACar.services.abstracts.DiscountService;
import com.rentACar.rentACar.services.abstracts.RentalService;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddUserDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.UpdateDiscountRequest;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountByIdResponse;
import com.rentACar.rentACar.services.dtos.responses.Discount.GetDiscountListResponse;
import com.rentACar.rentACar.services.rules.DiscountBusinessRules;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Service
public class DiscountManager implements DiscountService {
    private final DiscountRepository discountRepository;
    private final ModelMapperService modelMapperService;
    private final DiscountBusinessRules discountBusinessRules;
    @Override
    public List<GetDiscountListResponse> getAll() {
        List<Discount> discounts = discountRepository.findAll();
        List<GetDiscountListResponse> responses = discounts.stream().map(discount -> modelMapperService.forResponse().map(discount,GetDiscountListResponse.class))
                .collect(Collectors.toList());
        return responses;
    }
    @Override
    public GetDiscountByIdResponse getById(int id) {
        Discount discount = discountRepository.findById(id).orElseThrow();
        GetDiscountByIdResponse response = this.modelMapperService.forResponse().map(discount,GetDiscountByIdResponse.class);
        return response;
    }

    @Override
    public void add(AddDiscountRequest request) {
        Discount discount = this.modelMapperService.forRequest().map(request,Discount.class);
        discountRepository.save(discount);
    }

    @Override
    public void update(UpdateDiscountRequest request) {
        Discount discount = this.modelMapperService.forRequest().map(request,Discount.class);
        discountRepository.save(discount);
    }

    @Override
    public void delete(int id) {
        Discount discountToDelete = discountRepository.findById(id).orElseThrow();
        discountRepository.delete(discountToDelete);
    }


}
