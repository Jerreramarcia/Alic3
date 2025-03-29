package com.alic3.versioned.Catalog.Category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {


    @GetMapping(CategoryConstants.GET_CATALOGO_LIST)
    public String getCatalogo() {
        return "This is the catalog";
    }
}
