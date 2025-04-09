package com.alic3.versioned.catalog.service;

import com.alic3.versioned.catalog.domain.Category;
import com.alic3.versioned.catalog.dto.CategoryDTO;
import com.alic3.versioned.catalog.domain.SubCategory;
import com.alic3.versioned.catalog.domain.Product;
import com.alic3.versioned.catalog.repository.CategoryRepository;
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

    public void saveCategory(CategoryDTO categoryDTO) {
        try {
            categoryRepository.save(mapToEntity(categoryDTO));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Category mapToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());

        List<SubCategory> subcategories = dto.getSubCategories().stream()
                .map(subDTO -> {
                    SubCategory sub = new SubCategory();
                    sub.setId(subDTO.getId());
                    sub.setName(subDTO.getName());
                    sub.setCategory(category);

                    List<Product> products = subDTO.getProducts().stream()
                            .map(productDTO -> {
                                Product product = new Product();

                                product.setId(productDTO.getId());
                                product.setThumbnail(productDTO.getThumbnail());
                                product.setUnitPrice(productDTO.getUnitPrice());
                                product.setSizeFormat(productDTO.getPriceInstructions().getSizeFormat());
                                product.setUnitSize(productDTO.getPriceInstructions().getUnitSize());
                                product.setEan(productDTO.getEan());
                                product.setDisplay_name(productDTO.getDisplay_name());

                                product.setSubCategory(sub);
                                return product;
                            })
                            .toList();

                    sub.setProducts(products);
                    return sub;
                })
                .toList();

        category.setSubCategories(subcategories);
        return category;
    }

}
