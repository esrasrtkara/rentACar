package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.CorporateCustomer;
import com.rentACar.rentACar.entities.concretes.Model;
import com.rentACar.rentACar.repositories.CorporateCustomerRepository;
import com.rentACar.rentACar.services.abstracts.CorporateCustomerService;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;
    @Override
    public void add(AddCorporateCustomerRequest request) {

        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(request,CorporateCustomer.class);
        corporateCustomerRepository.save(corporateCustomer);

    }
}
