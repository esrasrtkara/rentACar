package com.rentACar.rentACar.controllers;

import com.rentACar.rentACar.services.abstracts.EmployeeService;
import com.rentACar.rentACar.services.dtos.requests.Employee.AddEmployeeRequest;
import com.rentACar.rentACar.services.dtos.requests.Employee.UpdateEmployeeRequest;
import com.rentACar.rentACar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentACar.rentACar.services.dtos.responses.Employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeesController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<GetEmployeeListResponse> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("{id}")
    public GetEmployeeResponse getById(@PathVariable int id){
        return employeeService.getById(id);
    }

    @PostMapping
    public void add(@RequestBody AddEmployeeRequest request){
        employeeService.add(request);
    }

    @PutMapping
    public void update(@RequestBody UpdateEmployeeRequest request){
        employeeService.update(request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        employeeService.delete(id);
    }
}
