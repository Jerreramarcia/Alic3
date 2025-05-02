package com.alic3.versioned.dto;

import com.alic3.versioned.model.Product;
import com.alic3.versioned.model.UserProduct;

import java.time.Instant;

public record UserProductDTO(long id,
                             long userId,
                             int quantity,
                             String info,
                             Instant createdAt,
                             String thumbnail,
                             String display_name
) {
    public UserProductDTO(UserProduct up, Product product) {
        this(
                up.getId(),
                up.getUserId(),
                up.getQuantity(),
                up.getInfo(),
                up.getCreatedAt(),
                product.getThumbnail(),
                product.getDisplay_name()
        );
    }

    public UserProductDTO(UserProduct up) {
        this(
                up.getId(),
                up.getUserId(),
                up.getQuantity(),
                up.getInfo(),
                up.getCreatedAt(),
                "", ""
        );
    }

    public Long getUserId() {
        return userId;
    }
}