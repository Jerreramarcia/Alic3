package com.alic3.versioned.controller;


import com.alic3.versioned.dto.UserProductDTO;
import com.alic3.versioned.service.UserProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.alic3.versioned.constants.ControllerConstants.MAPPING_USER_PRODUCT;

@RestController
@RequestMapping(value = MAPPING_USER_PRODUCT)
@AllArgsConstructor
@CrossOrigin("*")
public class UserProductController {

    UserProductService userProductService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserProductDTO>> getUserProducts(
            @PathVariable Long userId
    ) {

        List<UserProductDTO> foundUserProductList = userProductService.getUserProduct(userId);
        //We dont really want a list with the relations. we want a list o products per user.
        return ResponseEntity.ok(foundUserProductList);
    }

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<UserProductDTO> createUserProduct(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        UserProductDTO newRelation = new UserProductDTO(userProductService.create(userId, productId));

        URI location = URI.create(String.format(MAPPING_USER_PRODUCT + "/%d", newRelation.getUserId()));

        return ResponseEntity
                .created(location)
                .body(newRelation);
    }
}
