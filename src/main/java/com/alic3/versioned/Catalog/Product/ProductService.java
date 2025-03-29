package com.alic3.versioned.Catalog.Product;

import com.alic3.versioned.Catalog.Product.dto.ProductDTO;
import com.alic3.versioned.Catalog.Product.dto.ProductDetailsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://tienda.mercadona.es")
            .build();

    public List<ProductDTO> fetchAndEnrichProducts(Long subCategoryId) throws JsonProcessingException {
        JsonNode root = webClient.get()
                .uri("/api/categories/" + subCategoryId + "/products/")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        List<ProductDTO> basicProducts = Arrays.asList(
                new ObjectMapper().treeToValue(root.get("products"), ProductDTO[].class)
        );

        return basicProducts.stream()
                .map(this::enrichProduct)
                .toList();
    }

    private ProductDTO enrichProduct(ProductDTO base) {
        try {
            ProductDetailsDTO details = webClient.get()
                    .uri("/api/products/" + base.getId())
                    .retrieve()
                    .bodyToMono(ProductDetailsDTO.class)
                    .block();

            //base.setDescription(details.getDescription());
            //base.setOrigin(details.getOrigin());
            //base.setIngredients(details.getIngredients());

        } catch (Exception e) {
            System.err.println("❗ Error al enriquecer producto " + base.getId() + ": " + e.getMessage());
        }

        return base;
    }
}
