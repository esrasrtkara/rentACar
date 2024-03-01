package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.DiscountRepository;
import com.rentACar.rentACar.services.abstracts.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@AllArgsConstructor
public class DiscountBusinessRules {
    private final DiscountRepository discountRepository;



    public Float discount(int carId,String code) {
        Discount discountCar = discountRepository.findByCarId(carId).orElse(null);
        Discount discountCode = discountRepository.findByCode(code).orElse(null);

        LocalDate today = LocalDate.now();


        if (discountCode != null && !"".equals(discountCode)  && "PASIVE".equals(discountCode.getCodeStatus())) {
            throw new RuntimeException("Bu kod geçersiz!");
        } else {
            float carDiscountRate = (discountCar != null && isActiveDiscount(discountCar, today)) ? discountCar.getRate() : 0F;
            float userDiscountRate ;

           if (discountCode != null && !"".equals(discountCode)  && carDiscountRate <= discountCode.getRate() &&  code != null && code.equals(discountCode.getCode()) && isActiveDiscount(discountCode,today)) {
               userDiscountRate = discountCode.getRate();
               discountCode.setCodeStatus("PASIVE");
            }
           else {
               userDiscountRate =0F;
           }

            return Math.max(carDiscountRate, userDiscountRate);
        }


        }


    private boolean isActiveDiscount(Discount discount, LocalDate today) {
        return !today.isBefore(discount.getStartDate()) && !today.isAfter(discount.getEndDate());
    }



    }


