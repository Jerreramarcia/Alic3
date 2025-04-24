package com.alic3.versioned.service;


import com.alic3.versioned.model.User;
import com.alic3.versioned.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Data
@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    private final Supplier<String> randomString = () -> java.util.UUID.randomUUID().toString();
    private final Supplier<Long> randomLong = () -> java.util.concurrent.ThreadLocalRandom.current().nextLong();


    public User createUser() {

        UserCreatorInterface<String, String, String, User> Usrcreator = User::new;

        User user = Usrcreator.apply(
                "username_" + randomString.get(),
                "password_" + randomString.get(),
                "role" + randomString.get());

        userRepository.save(user);
        return user;
    }


    public User getUser(Long id) {



        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);

    }




}


/**
 * Functional Interface to create Users 4 testing.
 *
 * @param <T> String --> Username
 * @param <U> String --> Password
 * @param <V> String --> role
 * @param <R> Return --> new User(...params)
 */
@FunctionalInterface
interface UserCreatorInterface<T, U, V, R> {
    R apply(T t, U u, V v);
}