package com.alic3.versioned.catalog.repository;

import com.alic3.versioned.catalog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
