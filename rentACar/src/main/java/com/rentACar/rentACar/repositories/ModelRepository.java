package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    boolean existsByName(String name);
}
