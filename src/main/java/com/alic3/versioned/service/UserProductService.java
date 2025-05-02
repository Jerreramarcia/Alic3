package com.alic3.versioned.service;

import com.alic3.versioned.dto.UserProductDTO;
import com.alic3.versioned.model.Product;
import com.alic3.versioned.model.UserProduct;
import com.alic3.versioned.repository.UserProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserProductService {

    private final LongSupplier longSupplier = () -> Double.doubleToRawLongBits(Math.random());
    private final UserProductCreator<Long, Long, Integer, String, UserProduct> userProductCreator = UserProduct::new;

    private final UserProductRepository userProductRepository;
    private final ProductService productService;


    //Must return list of Products per user
    public List<UserProductDTO> getUserProduct(Long userId) {

        Optional<List<UserProduct>> foundList = userProductRepository.findByUserId(userId);

        if (foundList.isEmpty()) return null;

        List<UserProductDTO> toReturn = foundList.get().stream()
                .map(list -> {
                    Long productId = list.getProductId();
                    Product pr = productService.findProductById(productId);
                    return new UserProductDTO(list, pr);
                }).collect(Collectors.toList());

        return toReturn;

    }


    public UserProduct create(Object s, Object a) {
        UserProduct userProduct = userProductCreator.createBoughtProduct(longSupplier.getAsLong(), longSupplier.getAsLong(), 1, "");

        return userProductRepository.save(userProduct);

    }


    interface UserProductCreator<A, B, C, D, R> {
        R createBoughtProduct(A a, B b, C c, D d);
    }
}
