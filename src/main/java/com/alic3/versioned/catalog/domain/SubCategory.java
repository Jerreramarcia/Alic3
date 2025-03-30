package com.alic3.versioned.catalog.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategories")
@Data
public class SubCategory {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
}
