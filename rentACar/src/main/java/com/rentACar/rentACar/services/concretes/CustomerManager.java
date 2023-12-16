package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.entities.Customer;
import com.rentACar.rentACar.repositories.CustomerRepository;
import com.rentACar.rentACar.services.abstracts.CustomerService;
import com.rentACar.rentACar.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private UserService userService;
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
