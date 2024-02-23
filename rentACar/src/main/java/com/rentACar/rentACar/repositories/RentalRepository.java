package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    @Query("SELECT COUNT(r) FROM Rental r WHERE r.user.id = :userId")
    int countByUserId(int userId);



    List<Rental> findByUserId(int userId);

}
