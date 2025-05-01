package com.alic3.versioned.repository;

import com.alic3.versioned.model.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {

    List<UserProduct> findByUserId(Long id);
}
