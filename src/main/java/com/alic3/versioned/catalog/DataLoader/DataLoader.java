package com.alic3.versioned.catalog.DataLoader;

import com.alic3.versioned.catalog.service.CategoryService;
import com.alic3.versioned.catalog.dto.CategoryDTO;
import com.alic3.versioned.catalog.service.SubCategoryService;
import com.alic3.versioned.catalog.dto.SubCategoryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {


    @Value("${dataloader.enabled}") // true por defecto si no está definida
    private boolean enabled;

    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;


    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://tienda.mercadona.es")
            .build();


    @Override
    public void run(String... args) {

        if (!enabled) return;

        System.out.println("Fetching categories from Mercadona...");
        JsonNode root = webClient.get()
                .uri("/api/categories/")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        List<CategoryDTO> categories = null;
        try {
            categories = Arrays.asList(
                    mapper.treeToValue(root.get("results"), CategoryDTO[].class)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        int s = 1;
        for (CategoryDTO category : categories) {
            AtomicInteger i = new AtomicInteger();
            System.out.println("");
            System.out.println(s + "/" + categories.size() + " Categories");
            s++;


            List<SubCategoryDTO> subCategoryDTOS = category.getSubCategories();
            for (SubCategoryDTO subCategory : subCategoryDTOS) {
                System.out.println(i.get() + "/" + category.getSubCategories().size() + " SubCategories");
                i.getAndIncrement();
                try {
                    category.setSubCategories(subCategoryService.fillSubCategory(subCategory.getId()));
                } catch (JsonProcessingException e) {
                    System.out.println(e.getMessage());
                }
                categoryService.saveCategory(category);
            }

        }
            categoryService.saveCategories(categories);

        System.out.println("Categories successfully saved.");
    }
}
