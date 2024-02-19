package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Customer;
import com.rentACar.rentACar.entities.concretes.User;
import com.rentACar.rentACar.repositories.CustomerRepository;
import com.rentACar.rentACar.services.abstracts.CustomerService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerResponse;
import com.rentACar.rentACar.services.rules.CustomerBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private CustomerBusinessRules customerBusinessRules;
    private UserService userService;

    @Override
    public DataResult<List<GetCustomerListResponse>> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<GetCustomerListResponse> responses = customers.stream().map(customer -> modelMapperService.forResponse()
                .map(customer, GetCustomerListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetCustomerResponse> getById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        GetCustomerResponse response = this.modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddCustomerRequest request) {
        customerBusinessRules.checkIfUserId(request.getUserId());
        Customer customer = modelMapperService.forRequest().map(request, Customer.class);
        customer.setFirstName(request.getFirstName().toUpperCase());
        customer.setLastName(request.getLastName().toUpperCase());
        customerRepository.save(customer);
        return new SuccessResult(Messages.ADDED_CUSTOMER);
    }

    @Override
    public Result update(UpdateCustomerRequest request) {
        customerBusinessRules.checkIfUserId(request.getUserId());
        Customer customer = modelMapperService.forRequest().map(request, Customer.class);
        customer.setFirstName(request.getFirstName().toUpperCase());
        customer.setLastName(request.getLastName().toUpperCase());
        customerRepository.save(customer);
        return new SuccessResult(Messages.UPDATED_CUSTOMER);
    }

    @Override
    public Result delete(int id) {
        Customer customerToDelete = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customerToDelete);
        return new SuccessResult(Messages.DELETED_CUSTOMER);
    }

    public Customer getCustomer(){
        int userId =userService.userId(SecurityContextHolder.getContext().getAuthentication().getName());
        Customer customer = customerRepository.findByUserId(userId);
        return customer;
    }



}