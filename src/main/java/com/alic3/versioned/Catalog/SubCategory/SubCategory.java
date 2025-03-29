package com.alic3.versioned.Catalog.SubCategory;

import com.alic3.versioned.Catalog.Category.Category;
import jakarta.persistence.*;
import lombok.Data;

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
}
