package com.dev.whale.repository.main;

import com.dev.whale.domain.model.User;

import java.util.Optional;

public interface MainRepository {
    User save(User user);
    Optional<User> findById(String userId);
    User selectUser(User user);
    void insertUser(User user);
}
