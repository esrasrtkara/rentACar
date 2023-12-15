package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    boolean existsByName(String name);
}
