package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Brand;
import com.rentACar.rentACar.services.dtos.responses.Brand.GetBrandListResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByName(String name);
    List<Brand> findByDeletedFalse();
}
