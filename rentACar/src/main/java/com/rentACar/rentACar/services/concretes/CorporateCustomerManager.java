package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.CorporateCustomer;
import com.rentACar.rentACar.entities.concretes.Customer;
import com.rentACar.rentACar.entities.concretes.Model;
import com.rentACar.rentACar.repositories.CorporateCustomerRepository;
import com.rentACar.rentACar.services.abstracts.CorporateCustomerService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.AddCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.CorporateCustomer.UpdateCorporateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.CorporateCustomer.GetCorporateCustomerResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerResponse;
import com.rentACar.rentACar.services.rules.CorporateCustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;
    private final CorporateCustomerBusinessRules customerBusinessRules;
    private final UserService userService;

    @Override
    public DataResult<List<GetCorporateCustomerListResponse>> getAll() {
        List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
        List<GetCorporateCustomerListResponse> responses = corporateCustomers.stream().map(corporateCustomer -> modelMapperService.forResponse()
                .map(corporateCustomer, GetCorporateCustomerListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetCorporateCustomerResponse> getById(int id) {
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(id).orElseThrow();
        GetCorporateCustomerResponse response = this.modelMapperService.forResponse().map(corporateCustomer, GetCorporateCustomerResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddCorporateCustomerRequest request) {
        customerBusinessRules.checkIfUserId(request.getUserId());
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(request,CorporateCustomer.class);
        corporateCustomer.setCompanyName(request.getCompanyName().toUpperCase());
        corporateCustomerRepository.save(corporateCustomer);
        return new SuccessResult(Messages.ADDED_CORPORATE_CUSTOMER);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest request) {
        customerBusinessRules.checkIfUserId(request.getUserId());
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(request,CorporateCustomer.class);
        corporateCustomer.setCompanyName(request.getCompanyName().toUpperCase());
        corporateCustomerRepository.save(corporateCustomer);
        return new SuccessResult(Messages.UPDATED_CORPORATE_CUSTOMER);
    }

    @Override
    public Result delete(int id) {
        CorporateCustomer corporateCustomerToDelete = corporateCustomerRepository.findById(id).orElseThrow();
        corporateCustomerRepository.delete(corporateCustomerToDelete);
        return new SuccessResult(Messages.DELETED_CORPORATE_CUSTOMER);

    }

    @Override
    public CorporateCustomer getCorporateCustomer() {
        int userId =userService.userId(SecurityContextHolder.getContext().getAuthentication().getName());
        CorporateCustomer corporateCustomer = corporateCustomerRepository.findByUserId(userId);
        return corporateCustomer;
    }

}