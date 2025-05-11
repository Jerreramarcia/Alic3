package com.alic3.versioned.service;

import com.alic3.versioned.model.UserProduct;
import com.alic3.versioned.repository.UserProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.LongSupplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserProductServiceIntegrationTest {

    @Autowired
    UserProductRepository userProductRepository;

    final int ITEMS_SAVED = 20;

    final List<Long> productsID = List.of(
            15846L, 28400L, 28401L, 28356L, 28408L, 28494L, 25972L, 61233L,
            61252L, 61025L, 35221L, 61027L, 61034L, 61289L, 61238L, 35900L, 61261L
    );


    private final LongSupplier productIdSupplier = () -> productsID.get(ThreadLocalRandom.current().nextInt(productsID.size()));


    @Test
    void getUserProduct() {
    }

    @Test
    void createUserProduct_persistsUserProduct() {

        for (int i = 0; i < ITEMS_SAVED; i++) {
            UserProduct userProduct = new UserProduct();

            userProduct.setCreatedAt(Instant.now());
            userProduct.setUserId(0L);
            userProduct.setProductId(productIdSupplier.getAsLong());
            userProduct.setQuantity(ThreadLocalRandom.current().nextInt(1, 10));
            userProduct.setInfo("");
            UserProduct savedUserProduct = userProductRepository.save(userProduct);

            assertNotNull(savedUserProduct);
        }
    }
}