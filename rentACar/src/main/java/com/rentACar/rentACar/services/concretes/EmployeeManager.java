package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.utilities.mappers.ModelMapperService;
import com.rentACar.rentACar.entities.Employee;
import com.rentACar.rentACar.repositories.EmployeeRepository;
import com.rentACar.rentACar.services.abstracts.EmployeeService;
import com.rentACar.rentACar.services.abstracts.UserService;
import com.rentACar.rentACar.services.dtos.requests.Employee.AddEmployeeRequest;
import com.rentACar.rentACar.services.dtos.requests.Employee.UpdateEmployeeRequest;
import com.rentACar.rentACar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentACar.rentACar.services.dtos.responses.Employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;
    private UserService userService;

    @Override
    public List<GetEmployeeListResponse> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<GetEmployeeListResponse> responses = employees.stream().map(employee -> modelMapperService.forResponse()
                .map(employee, GetEmployeeListResponse.class)).collect(Collectors.toList());
        return responses;
    }

    @Override
    public GetEmployeeResponse getById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        GetEmployeeResponse response = this.modelMapperService.forResponse().map(employee, GetEmployeeResponse.class);
        return response;
    }

    @Override
    public void add(AddEmployeeRequest request) {
        Employee employee = modelMapperService.forRequest().map(request, Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public void update(UpdateEmployeeRequest request) {
        Employee employee = modelMapperService.forRequest().map(request, Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public void delete(int id) {
        Employee employeeToDelete = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employeeToDelete);
    }

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