package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRateRepository extends JpaRepository<TaxRate,Integer> {
}
