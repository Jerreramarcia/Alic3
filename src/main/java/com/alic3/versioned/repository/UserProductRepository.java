package com.alic3.versioned.repository;

import com.alic3.versioned.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {

    Optional<List<UserProduct>> findByUserId(Long id);
}
