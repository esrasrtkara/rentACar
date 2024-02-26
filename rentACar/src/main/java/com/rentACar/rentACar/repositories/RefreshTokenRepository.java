package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    RefreshToken findByUserId(int userId);
}
