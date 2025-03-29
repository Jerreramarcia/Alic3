package com.alic3.versioned.Catalog.Category;

import com.alic3.versioned.Catalog.SubCategory.SubCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SubCategory> subCategories = new ArrayList<>();

}
