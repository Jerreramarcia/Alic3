package com.alic3.versioned.Catalog.DataLoader;

import com.alic3.versioned.Catalog.Category.CategoryService;
import com.alic3.versioned.Catalog.Category.dto.CategoryDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {


    private final CategoryService categoryService;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://tienda.mercadona.es")
            .build();


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Fetching categories from Mercadona...");

        try {
            JsonNode root = webClient.get()
                    .uri("/api/categories/")
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            ObjectMapper mapper = new ObjectMapper();
            List<CategoryDTO> categories = Arrays.asList(
                    mapper.treeToValue(root.get("results"), CategoryDTO[].class)
            );

            categoryService.saveCategories(categories);

            System.out.println("Categories successfully saved.");
        } catch (Exception e) {
            System.err.println("Error fetching categories: " + e.getMessage());
        }
    }
}
