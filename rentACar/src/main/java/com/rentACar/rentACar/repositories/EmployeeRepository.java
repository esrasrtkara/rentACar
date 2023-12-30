package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
