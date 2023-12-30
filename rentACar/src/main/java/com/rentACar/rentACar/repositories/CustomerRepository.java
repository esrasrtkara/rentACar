package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
