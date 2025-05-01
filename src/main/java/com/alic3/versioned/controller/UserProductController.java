package com.alic3.versioned.controller;


import com.alic3.versioned.model.UserProduct;
import com.alic3.versioned.service.UserProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import static com.alic3.versioned.constants.ControllerConstants.MAPPING_USER_PRODUCT;

@RestController
@RequestMapping(value = MAPPING_USER_PRODUCT)
@AllArgsConstructor
@CrossOrigin("*")
public class UserProductController {

    UserProductService userProductService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserProductDto>> getUserProducts(
            @PathVariable Long userId
    ) {

        List<UserProduct> foundUserProductList = userProductService.getUserProduct(userId);
        List<UserProductDto> userProductDtoList = foundUserProductList.stream().map(UserProductDto::new).toList();

        return ResponseEntity.ok(userProductDtoList);
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<UserProductDto> createUserProduct(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        UserProductDto newRelation = new UserProductDto(userProductService.create(userId, productId));

        URI location = URI.create(String.format(MAPPING_USER_PRODUCT + "/%d", newRelation.getUserId()));

        return ResponseEntity
                .created(location)
                .body(newRelation);
    }


    record UserProductDto(long id,
                          Long userId,
                          Long productId,
                          int quantity,
                          String info,
                          Instant createdAt
    ) {
        public UserProductDto(UserProduct up) {
            this(
                    up.getId(),
                    up.getUserId(),
                    up.getProductId(),
                    up.getQuantity(),
                    up.getInfo(),
                    up.getCreatedAt()
            );
        }

        public Long getUserId() {
            return userId;
        }
    }
}
