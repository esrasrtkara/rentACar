package com.rentACar.rentACar.services.rules;

import com.rentACar.rentACar.services.abstracts.RentalService;
import com.rentACar.rentACar.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final RentalService rentalService;

    public void checkIfRentalId(int id){
        if(!rentalService.controlRentalId(id)){
            throw new RuntimeException(Messages.CHECK_IF_RENTAL_ID);
        }
    }

}
