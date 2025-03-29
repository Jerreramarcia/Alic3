package com.alic3.versioned.Catalog.SubCategory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDTO {
    private Long id;
    private String name;
}