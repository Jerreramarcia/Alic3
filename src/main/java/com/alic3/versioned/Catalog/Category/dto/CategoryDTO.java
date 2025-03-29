package com.alic3.versioned.Catalog.Category.dto;

import com.alic3.versioned.Catalog.SubCategory.dto.SubCategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {
    private Long id;
    private String name;
    private List<SubCategoryDTO> categories;
}
