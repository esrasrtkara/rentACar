package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerRepository  extends JpaRepository<CorporateCustomer,Integer> {
    CorporateCustomer findByUserId(int userId);
}
