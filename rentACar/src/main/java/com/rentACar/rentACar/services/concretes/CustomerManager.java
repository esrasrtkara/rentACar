package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.Customer;
import com.rentACar.rentACar.repositories.CustomerRepository;
import com.rentACar.rentACar.services.abstracts.CustomerService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentACar.rentACar.services.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentACar.rentACar.services.dtos.responses.Customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;
    private UserService userService;

    @Override
    public List<GetCustomerListResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<GetCustomerListResponse> responses = customers.stream().map(customer -> modelMapperService.forResponse()
                .map(customer, GetCustomerListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetCustomerResponse getById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        GetCustomerResponse response = this.modelMapperService.forResponse().map(customer, GetCustomerResponse.class);
        return response;
    }

    @Override
    public void add(AddCustomerRequest request) {
        Customer customer = modelMapperService.forRequest().map(request, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void update(UpdateCustomerRequest request) {
        Customer customer = modelMapperService.forRequest().map(request, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        Customer customerToDelete = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customerToDelete);
    }

    @Override
    public boolean controlCustomerUserId(int id) {
        Customer customer ;
        try{
            customer = customerRepository.findById(id).orElseThrow();
        }catch (NoSuchElementException e){
            return false;
        }
        return   userService.controlUserId(customer.getUser().getId());
    }
}