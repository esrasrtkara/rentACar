package com.rentACar.rentACar.services.abstracts;

import com.rentACar.rentACar.services.dtos.requests.Employee.AddEmployeeRequest;
import com.rentACar.rentACar.services.dtos.requests.Employee.UpdateEmployeeRequest;
import com.rentACar.rentACar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentACar.rentACar.services.dtos.responses.Employee.GetEmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<GetEmployeeListResponse> getAll();
    GetEmployeeResponse getById(int id);
    void add(AddEmployeeRequest request);
    void update(UpdateEmployeeRequest request);
    void delete(int id);
    boolean controlEmployeeUserId(int id);
}
