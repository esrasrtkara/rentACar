package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
}
