package com.rentACar.rentACar.repositories;

import com.rentACar.rentACar.entities.concretes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}