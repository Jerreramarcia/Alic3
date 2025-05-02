package com.alic3.versioned.service;

import com.alic3.versioned.config.WebClientHelper;
import com.alic3.versioned.model.Product;
import com.alic3.versioned.dto.ProductDTO;
import com.alic3.versioned.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    WebClientHelper webClientHelper;

    ProductRepository productRepository;

    public ProductDTO fillSubCategoryProduct(Long productId) throws JsonProcessingException {
        return webClientHelper.fetchWithRetry("/api/products/" + productId, ProductDTO.class);
    }



    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    /**
     * Repository usage
     */

    public ProductDTO findProductByEan(String ean) {
        Optional<Product> product = productRepository.findByEan(ean);
        return product.map(this::fromDB).orElse(null);
    }


    private ProductDTO fromDB(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDisplay_name(product.getDisplay_name());
        productDTO.setEan(product.getEan());
        productDTO.setThumbnail(product.getThumbnail());
        ProductDTO.PriceInstructions priceInstructions = new ProductDTO.PriceInstructions();
        priceInstructions.setUnitPrice(product.getUnitPrice());
        priceInstructions.setSizeFormat(product.getSizeFormat());
        priceInstructions.setUnitSize(product.getUnitSize());
        productDTO.setPriceInstructions(priceInstructions);

        return productDTO;

    }

}
