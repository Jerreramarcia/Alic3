package com.alic3.versioned.api.controller;

import com.alic3.versioned.catalog.dto.ProductDTO;
import com.alic3.versioned.catalog.service.ProductService;
import com.alic3.versioned.constants.ControllerConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = ControllerConstants.MAPPING_PRODUCT)
@AllArgsConstructor
public class ProductController {

    ProductService productService;

    @GetMapping(value = ControllerConstants.GET_PRODUCT_BY_EAN)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String ean) {
        ProductDTO productDTO = productService.findProductByEan(ean);
        return ResponseEntity.ok().body(productDTO);
    }

}
