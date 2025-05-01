package com.alic3.versioned.service;

import com.alic3.versioned.model.UserProduct;
import com.alic3.versioned.repository.UserProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.LongSupplier;

@Service
@AllArgsConstructor
public class UserProductService {

    private final LongSupplier longSupplier = () -> Double.doubleToRawLongBits(Math.random());
    private final UserProductCreator<Long, Long, Integer, String, UserProduct> userProductCreator = UserProduct::new;

    private final UserProductRepository userProductRepository;

    public List<UserProduct> getUserProduct(Long userId) {

        return userProductRepository.findByUserId(userId);
    }

    
    public UserProduct create(Object s, Object a)
    {
        UserProduct userProduct = userProductCreator.createBoughtProduct(longSupplier.getAsLong(),longSupplier.getAsLong(),1,"");

        return userProductRepository.save(userProduct);

    }
    
    

    interface UserProductCreator<A, B, C, D, R> {
        R createBoughtProduct(A a, B b, C c, D d);
    }
}
