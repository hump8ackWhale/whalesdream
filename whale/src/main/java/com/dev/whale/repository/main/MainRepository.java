package com.dev.whale.repository.main;

import com.dev.whale.domain.model.User;

import java.util.Optional;

public interface MainRepository {
    User join(User user);
    Optional<User> findByName(String userName);
}
