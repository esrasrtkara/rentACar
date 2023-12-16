package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.entities.Employee;
import com.rentACar.rentACar.repositories.EmployeeRepository;
import com.rentACar.rentACar.services.abstracts.EmployeeService;
import com.rentACar.rentACar.services.abstracts.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private UserService userService;
    @Override
    public boolean controlEmployeeUserId(int id) {
        Employee employee;
        try {
            employee = employeeRepository.findById(id).orElseThrow();
        }catch (NoSuchElementException e){
            return  false;
        }
        return userService.controlUserId(employee.getUser().getId());
    }
}
