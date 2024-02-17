package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    boolean existsByName(String name);

    List<Model> findByDeletedFalse();
}
