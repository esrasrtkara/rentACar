package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.entities.concretes.CorporateCustomer;
import com.rentACar.rentACar.entities.concretes.Customer;
import com.rentACar.rentACar.entities.concretes.Model;
import com.rentACar.rentACar.repositories.CorporateCustomerRepository;
import com.rentACar.rentACar.services.abstracts.CorporateCustomerService;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.UpdateCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerResponse;
import com.rentACar.rentACar.services.rules.CorporateCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;
    private final CorporateCustomerBusinessRules customerBusinessRules;

    @Override
    public List<GetCorporateCustomerListResponse> getAll() {
        List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
        List<GetCorporateCustomerListResponse> responses = corporateCustomers.stream().map(corporateCustomer -> modelMapperService.forResponse()
                .map(corporateCustomer, GetCorporateCustomerListResponse.class)).collect(Collectors.toList());
        return responses;

    }

    @Override
    public GetCorporateCustomerResponse getById(int id) {
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(id).orElseThrow();
        GetCorporateCustomerResponse response = this.modelMapperService.forResponse().map(corporateCustomer, GetCorporateCustomerResponse.class);
        return response;
    }

    @Override
    public void add(AddCorporateCustomerRequest request) {
        customerBusinessRules.checkIfUserId(request.getUserId());
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(request,CorporateCustomer.class);
        corporateCustomer.setCompanyName(request.getCompanyName().toUpperCase());
        corporateCustomerRepository.save(corporateCustomer);
    }

    @Override
    public void update(UpdateCorporateCustomerRequest request) {
        customerBusinessRules.checkIfUserId(request.getUserId());
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(request,CorporateCustomer.class);
        corporateCustomer.setCompanyName(request.getCompanyName().toUpperCase());
        corporateCustomerRepository.save(corporateCustomer);
    }

    @Override
    public void delete(int id) {
        CorporateCustomer corporateCustomerToDelete = corporateCustomerRepository.findById(id).orElseThrow();
        corporateCustomerRepository.delete(corporateCustomerToDelete);

    }
}
