package com.alic3.versioned.controller;

import com.alic3.versioned.dto.ProductDTO;
import com.alic3.versioned.service.ProductService;
import com.alic3.versioned.constants.ControllerConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = ControllerConstants.MAPPING_PRODUCT)
@AllArgsConstructor
@CrossOrigin("*")
public class ProductController {

    ProductService productService;

    @GetMapping(value = ControllerConstants.GET_PRODUCT_BY_EAN)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String ean) {
        ProductDTO productDTO = productService.findProductByEan(ean);
        return ResponseEntity.ok().body(productDTO);
    }

}
