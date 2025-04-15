package com.alic3.versioned.service;

import com.alic3.versioned.dto.SubCategoryDTO;
import com.alic3.versioned.dto.ProductDTO;
import com.alic3.versioned.config.WebClientHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubCategoryService {

    ProductService productService;
    WebClientHelper webClientHelper;

    public List<SubCategoryDTO> fillSubCategory(Long subCategoryId) throws JsonProcessingException {
        JsonNode root = webClientHelper.fetchWithRetry("/api/categories/" + subCategoryId, JsonNode.class);

        List<SubCategoryDTO> subCategoryDTOSList = Arrays.asList(
                new ObjectMapper().treeToValue(root.get("categories"), SubCategoryDTO[].class)
        );


        for (SubCategoryDTO subCategoryDTO : subCategoryDTOSList) {
            AtomicInteger i = new AtomicInteger();
            List<ProductDTO> enrichedList = subCategoryDTO.getProducts().stream()
                    .map(p -> {
                        try {
                            System.out.print("\r   " + i.get() + "/" + subCategoryDTO.getProducts().size() + " Products");
                            i.getAndIncrement();
                            return productService.fillSubCategoryProduct(p.getId());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());

            subCategoryDTO.setProducts(enrichedList);
        }


        return subCategoryDTOSList;
    }


}
