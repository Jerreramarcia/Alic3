package com.alic3.versioned.catalog.repository;

import com.alic3.versioned.catalog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryProductRepository extends JpaRepository<Product, Integer> {
}
