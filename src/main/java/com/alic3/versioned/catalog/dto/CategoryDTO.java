package com.alic3.versioned.catalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {
    private Long id;
    private String name;

    @JsonProperty("categories")
    private List<SubCategoryDTO> subCategories;
}
