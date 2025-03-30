package com.alic3.versioned.catalog.service;

import com.alic3.versioned.catalog.dto.ProductDTO;
import com.alic3.versioned.catalog.WebClientHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    WebClientHelper webClientHelper;

    public ProductDTO fillSubCategoryProduct(Long productId) throws JsonProcessingException {
        return webClientHelper.fetchWithRetry("/api/products/" + productId, ProductDTO.class);
    }

}
