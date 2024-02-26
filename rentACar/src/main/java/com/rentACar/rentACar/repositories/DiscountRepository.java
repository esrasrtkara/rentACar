package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount,Integer> {


    Optional<Discount> findByCarId(int carId);
    Optional<Discount> findByCode(String code);

    List<Discount> findByUserId(int userId);

    List<Discount> findByUserIdAndCodeStatusNull(int userId);

    List<Discount> findByCarIdNotNull();
}
