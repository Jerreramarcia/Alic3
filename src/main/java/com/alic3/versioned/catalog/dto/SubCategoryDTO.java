package com.alic3.versioned.catalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;
}