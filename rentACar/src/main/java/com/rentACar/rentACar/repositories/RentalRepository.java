package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    Optional<Rental> findByUserId(int userId);
}
