package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
