package com.alic3.versioned.catalog.repository;

import com.alic3.versioned.catalog.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}
