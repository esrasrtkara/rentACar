package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository  extends JpaRepository<Car,Integer> {
    boolean existsByPlate(String plate);
    List<Car> findByDeletedFalse();


}
