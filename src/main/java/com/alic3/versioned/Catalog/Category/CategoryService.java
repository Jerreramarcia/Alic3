package com.alic3.versioned.Catalog.Category;

import com.alic3.versioned.Catalog.Category.dto.CategoryDTO;
import com.alic3.versioned.Catalog.SubCategory.SubCategory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    CategoryRepository categoryRepository;


    public void saveCategories(List<CategoryDTO> categoriesDTO) {

        List<Category> categories = categoriesDTO.stream().map(this::mapToEntity).toList();

        categoryRepository.saveAll(categories);
    }


    private Category mapToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());

        List<SubCategory> subcategories = dto.getCategories().stream()
                .map(subDTO -> {
                    SubCategory sub = new SubCategory();
                    sub.setId(subDTO.getId());
                    sub.setName(subDTO.getName());
                    sub.setCategory(category);
                    return sub;
                })
                .toList();

        category.setSubCategories(subcategories);
        return category;
    }
}
