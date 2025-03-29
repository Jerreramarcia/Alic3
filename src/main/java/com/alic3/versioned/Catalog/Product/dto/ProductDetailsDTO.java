package com.alic3.versioned.Catalog.Product.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailsDTO {
    private String id;
    private String description;
    private String origin;
    private String ingredients;
    private String ean = "";
}